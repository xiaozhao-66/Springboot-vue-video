<template>
    <transition
            name="dialog-fade"
            @after-enter="afterEnter"
            @after-leave="afterLeave">
        <div
                v-show="visible"
                :class="{dialog__wrapper:wrapper}">
            <div
                    role="dialog"
                    :key="key"
                    ref="dialog"
                    :class="['dialog', {'dialog__center': center }, customClass]"
            >
                <div
                        class="dialog_head"
                        style="cursor: move;"
                        @mousedown="handPageDrag">
                    <slot name="title">
                        <span class="dialog_title">{{ title }}</span>
                    </slot>
                    <div class="dialog_header_group">
                        <button
                                type="button"
                                v-if="shrink"
                                @click="handleShrink"
                                aria-label="shrink"
                                class="dialog_header_btn">
                            <i :class="iconMaxClass"></i>
                        </button>
                        <button
                                type="button"
                                v-if="showClose"
                                @click="handleClose"
                                aria-label="Close"
                                class="dialog_header_btn">
                            <i class="m-icon-close"></i>
                        </button>

                    </div>

                </div>
                <div class="dialog_body dialog_content" v-show="isShrink">
                    <slot></slot>
                </div>
                <div class="dialog_foote " v-show="isShrink">
            <span class="dialog-footer">
                 <slot name="footer"></slot>
            </span>
                </div>
            </div>
        </div>
    </transition>
</template>

<script>
    import Popup from '../../util/popup/index'
    import emitter from '../../mixins/emitter'
    import {layerPosition,layerDrag} from '../../util/layer'


    export default {
        name: "m-dialog",
        //??????
        mixins: [Popup, emitter],
        props: {
            title: {
                type: String,
                default: ''
            },
            appendToBody: {
                type: Boolean,
                default: false
            },
            showClose: {
                type: Boolean,
                default: true
            },
            //????????????
            shrink: {
                type: Boolean,
                default: true,
            },
            customClass: {
                type: String,
                default: ''
            },
            beforeClose: Function,
            center: {
                type: Boolean,
                default: true
            },
            destroyOnClose: Boolean
        },
        data() {
            return {
                wrapper: false,
                closed: false,
                isShrink: true,
                key: 0
            };
        },
        computed: {
            iconMaxClass() {
                return !this.isShrink ? "m-icon-maxus" : "m-icon-minus";
            }
        },
        watch: {
            // ????????????
            visible(val) {
                if (val) {
                    this.closed = false;
                    this.$emit('open');
                    this.$el.addEventListener('scroll', this.updatePopper);
                    this.$nextTick(() => {
                        this.resetDialogPosition(true);
                        this.$refs.dialog.scrollTop = 0;
                    });
                    if (this.appendToBody) {
                        document.body.appendChild(this.$el);
                    }
                } else {
                    this.$el.removeEventListener('scroll', this.updatePopper);
                    if (!this.closed) this.$emit('close');
                    if (this.destroyOnClose) {
                        this.$nextTick(() => {
                            this.key++;
                        });
                    }
                }
            }
        },

        methods: {
            resetDialogPosition(flag) {
                let el = this.$refs.dialog;
                if (flag) {
                    layerPosition(el,"center");
                } else {
                    layerPosition(el,"lt");

                }
            },
            handPageDrag(e) {
                let el = this.$refs.dialog;
                layerDrag(e,el)
            },
            handleShrink() {
                this.isShrink = !this.isShrink;
                this.$nextTick(() => {
                    this.resetDialogPosition(this.isShrink);
                })
            },
            handleClose() {
                if (typeof this.beforeClose === 'function') {
                    this.beforeClose(this.hide);
                } else {
                    this.hide();
                }
            },
            // ??????
            hide(cancel) {
                if (cancel !== false) {
                    this.$emit('update:visible', false);
                    this.$emit('close');
                    this.closed = true;
                }
            },
            // ??????????????????????????????????????????z-index ??????
            updatePopper() {
                /*        this.broadcast('ElSelectDropdown', 'updatePopper');
                        this.broadcast('ElDropdownMenu', 'updatePopper');*/
            },
            afterEnter() {
                this.$emit('opened');
            },
            afterLeave() {
                this.$emit('closed');
            },
        },
        mounted() {
            this.resetDialogPosition(true)
        },
    }
</script>

