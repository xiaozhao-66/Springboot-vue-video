import{e as r,a4 as i,a5 as l,m as d,l as _,n as u,o as p,c as m}from"../app.4ba9a0c4.js";const A=r({__name:"VPCarbonAds",setup(f){const{theme:o}=i(),a=o.value.carbonAds,{isAsideEnabled:n}=l(),s=d();let t=!1;function c(){if(!t){t=!0;const e=document.createElement("script");e.id="_carbonads_js",e.src=`//cdn.carbonads.com/carbon.js?serve=${a.code}&placement=${a.placement}`,e.async=!0,s.value.appendChild(e)}}return a&&_(()=>{n.value?c():u(n,e=>e&&c())}),(e,b)=>(p(),m("div",{class:"VPCarbonAds",ref_key:"container",ref:s},null,512))}});export{A as default};