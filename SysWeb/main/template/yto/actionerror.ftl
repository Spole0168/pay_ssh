<#if (actionErrors?exists && actionErrors?size > 0)>
<div>
<#list actionErrors as error>
<span class="errorMessage">${error}</span><br/>
</#list>
</div>
</#if> 