<#macro head>
    <#include "lib.ftl"/>
    <#nested>
</#macro>
<#macro body>
    <#include "head.ftl"/>
    <#nested>
    <#include "foot.ftl"/>
</#macro>
