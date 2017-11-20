上传的jquery-ui-1.8.4.custom.js中的4825行-4859行为我所加入的内容
4864-4881行和5149-5160行是添加的关于autocomplate功能
主要是由于autocomplete的ui在firefox下输入中文无法触发keyup事件，加入这些代码之后，我测试无问题
待架构组或测试人员测试后，可修改*.min.js文件