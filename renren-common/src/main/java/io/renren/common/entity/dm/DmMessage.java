package io.renren.common.entity.dm;

import lombok.Data;

@Data
public class DmMessage {

    private String toAddress;

    private String content;
}
