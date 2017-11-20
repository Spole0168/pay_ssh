//--身份证号码验证-支持新的带x身份证
function isIdCardNo(num) {
    var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
    var error;
    var varArray = new Array();
    var intValue;
    var lngProduct = 0;
    var intCheckDigit;
    var intStrLen = num.length;
    var idNumber = num;
    // initialize
    if ((intStrLen != 15) && (intStrLen != 18)) {
        //error = "输入身份证号码长度不对！";
        //alert(error);
        return false;
    }
    // check and set value
    for (i = 0; i < intStrLen; i++) {
        varArray[i] = idNumber.charAt(i);
        if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
            //error = "错误的身份证号码！.";
            //alert(error);
            return false;
        } else if (i < 17) {
            varArray[i] = varArray[i] * factorArr[i];
        }
    }
    if (intStrLen == 18) {
        //check date
        var date8 = idNumber.substring(6, 14);
        if (checkDate(date8) == false) {
            //error = "身份证中日期信息不正确！.";
            //alert(error);
            return false;
        }
        
        var reg = /\d|x|X/;
        if(reg.exec(idNumber.charAt(17)) == null){
        	return false;
        }
    } else { //length is 15
        //check date
        var date6 = idNumber.substring(6, 12);
        if (checkDate(date6) == false) {
            //alert("身份证日期信息有误！.");
            return false;
        }
    }
    
    return true;
}

//判断输入的日期是否正确
function checkDate(INDate) {
    if (INDate == "") {
        return true;
    }
    subYY = INDate.substr(0, 4)
    if (isNaN(subYY) || subYY <= 0) {
        return true;
    }
    //转换月份
    if (INDate.indexOf('-', 0) != -1) {
        separate = "-"
    } else {
        if (INDate.indexOf('/', 0) != -1) {
            separate = "/"
        } else {
            return true;
        }
    }
    area = INDate.indexOf(separate, 0)
    subMM = INDate.substr(area + 1, INDate.indexOf(separate, area + 1) - (area + 1))
    if (isNaN(subMM) || subMM <= 0) {
        return true;
    }
    if (subMM.length < 2) {
        subMM = "0" + subMM
    }
    //转换日
    area = INDate.lastIndexOf(separate)
    subDD = INDate.substr(area + 1, INDate.length - area - 1)
    if (isNaN(subDD) || subDD <= 0) {
        return true;
    }
    if (eval(subDD) < 10) {
        subDD = "0" + eval(subDD)
    }
    NewDate = subYY + "-" + subMM + "-" + subDD
    if (NewDate.length != 10) {
        return true;
    }
    if (NewDate.substr(4, 1) != "-") {
        return true;
    }
    if (NewDate.substr(7, 1) != "-") {
        return true;
    }
    var MM = NewDate.substr(5, 2);
    var DD = NewDate.substr(8, 2);
    if ((subYY % 4 == 0 && subYY % 100 != 0) || subYY % 400 == 0) { //判断是否为闰年
        if (parseInt(MM) == 2) {
            if (DD > 29) {
                return true;
            }
        }
    } else {
        if (parseInt(MM) == 2) {
            if (DD > 28) {
                return true;
            }
        }
    }
    var mm = new Array(1, 3, 5, 7, 8, 10, 12); //判断每月中的最大天数
    for (i = 0; i < mm.length; i++) {
        if (parseInt(MM) == mm[i]) {
            if (parseInt(DD) > 31) {
                return true;
            }
        } else {
            if (parseInt(DD) > 30) {
                return true;
            }
        }
    }
    if (parseInt(MM) > 12) {
        return true;
    }
    return false;
}

String.prototype.isEmpty = function() {
	if (typeof this == 'undefined' || this == null || this == "")
		return true;
	return false;
}

String.prototype.isIdCardNo = function() {
	return isIdCardNo(this);
}

String.prototype.isEnglish = function() {
	return /^[A-Za-z]+$/.test(this);
}

String.prototype.isChinese = function() {
	//return /^[\u4E00-\u9FA5]+$/.test(this);
	return /^[\uFF00-\uFFFF\u0391-\uFFE5]+$/.test(this);
}

String.prototype.isEnNum = function() {
	return /^[A-Za-z0-9]+$/.test(this);
}

String.prototype.isNumber = function() {
	return /^\d+(\.\d+)?$/.test(this);
}

String.prototype.isLegal = function() {
	return /^[\'\"\:\;\<\>\,\?\/\`\~\!\@\#\$\%\^\&\*\(\)\[\]\{\}\|\\\+\-\_\=\,\.\/\sA-Za-z0-9\u0391-\uFFE5]+$/.test(this);
	//return /^[u0391-uFFE5\w]+$/.test(this);
}

String.prototype.isLegalHostSerialNo = function() {
	return /^[A-Za-z0-9\-]+$/.test(this);
}

/*
* UTF ORACLE  中文占3个字符
* 中文和英文以一个字节计算
*/
String.prototype.len = function() {
	return this.replace(/[^\x00-\xff]/g, 'x').length;
}

//电话号码：可以包含数字以及"-"，并且一定要以数字开头数字结尾
String.prototype.isTelNo = function() {
	return /^[0-9][0-9-]*[0-9]$/.test(this);
}

// 添加验证方法 (身份证号码验证)
jQuery.validator.addMethod("isIdCardNo", function (value, element) {
	return this.optional(element) || value.isIdCardNo();
}, "请正确输入您的身份证号码");

// 字符最小长度验证（一个中文字符长度为2）
jQuery.validator.addMethod("stringMinLength", function (value, element, param) {
	return this.optional(element) || (value.len() >= param);
}, $.validator.format("长度不能小于{0}！"));

// 字符最大长度验证（一个中文字符长度为2）
jQuery.validator.addMethod("stringMaxLength", function (value, element, param) {
	return this.optional(element) || (value.len() <= param);
}, $.validator.format("长度不能大于{0}！"));

// 字符长度验证
jQuery.validator.addMethod("stringEqualLength", function (value, element, param) {
	return this.optional(element) || (value.len() == param);
}, $.validator.format("长度必须为{0}！"));

// 中文字两个字节
jQuery.validator.addMethod("stringRangeLength", function (value, element, param) {
	return this.optional(element) || (value.len() >= param[0] && value.len() <= param[1]);
}, "长度在{0}和{1}之间！");

jQuery.validator.addMethod("isChinese", function (value, element) {
	return this.optional(element) || value.isChinese();
}, "只能输入汉字");

jQuery.validator.addMethod("isEnglish", function (value, element) {
	return this.optional(element) || value.isEnglish();
}, "只能输入字母");

jQuery.validator.addMethod("isEnNum", function(value, element) {
    return this.optional(element) || value.isEnNum();  
}, "只能输入字母和数字");

jQuery.validator.addMethod("isNumber", function(value, element) {
    return this.optional(element) || value.isNumber();  
}, "只能输入数字");

jQuery.validator.addMethod("isLegal", function (value, element) {
	return this.optional(element) || value.isLegal();
}, "不允许包含特殊符号!");

jQuery.validator.addMethod("isLegalHostSerialNo", function (value, element) {
	return this.optional(element) || value.isLegalHostSerialNo();
}, "只允许数据字母、数字、-符号!");

jQuery.validator.addMethod("isTelNo", function (value, element) {
	return this.optional(element) || value.isTelNo();
}, "请输入正确的电话号码。");

//---------------------------------------------------
//日期格式化
//格式 YYYY/yyyy/YY/yy 表示年份
//MM/M 月份
//W/w 星期
//dd/DD/d/D 日期
//hh/HH/h/H 时间
//mm/m 分钟
//ss/SS/s/S 秒
//---------------------------------------------------
Date.prototype.format = function(formatStr)
{
	var str = formatStr;
	var Week = ['日','一','二','三','四','五','六'];
	
	str=str.replace(/yyyy|YYYY/,this.getFullYear());
	str=str.replace(/yy|YY/,(this.getYear() % 100)>9?(this.getYear() % 100).toString():'0' + (this.getYear() % 100));
	
	str=str.replace(/MM/,this.getMonth()>9?this.getMonth().toString():'0' + this.getMonth());
	str=str.replace(/M/g,this.getMonth());
	
	str=str.replace(/w|W/g,Week[this.getDay()]);
	
	str=str.replace(/dd|DD/,this.getDate()>9?this.getDate().toString():'0' + this.getDate());
	str=str.replace(/d|D/g,this.getDate());
	
	str=str.replace(/hh|HH/,this.getHours()>9?this.getHours().toString():'0' + this.getHours());
	str=str.replace(/h|H/g,this.getHours());
	str=str.replace(/mm/,this.getMinutes()>9?this.getMinutes().toString():'0' + this.getMinutes());
	str=str.replace(/m/g,this.getMinutes());
	
	str=str.replace(/ss|SS/,this.getSeconds()>9?this.getSeconds().toString():'0' + this.getSeconds());
	str=str.replace(/s|S/g,this.getSeconds());
	
	return str;
}

//+---------------------------------------------------
//| 日期计算
//+---------------------------------------------------
Date.prototype.add = function(strInterval, Number) {
	var dtTmp = this;
	switch (strInterval) {
		case 's' :return new Date(Date.parse(dtTmp) + (1000 * Number));
		case 'n' :return new Date(Date.parse(dtTmp) + (60000 * Number));
		case 'h' :return new Date(Date.parse(dtTmp) + (3600000 * Number));
		case 'd' :return new Date(Date.parse(dtTmp) + (86400000 * Number));
		case 'w' :return new Date(Date.parse(dtTmp) + ((86400000 * 7) * Number));
		case 'q' :return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number*3, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
		case 'm' :return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
		case 'y' :return new Date((dtTmp.getFullYear() + Number), dtTmp.getMonth(), dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds());
	}
}