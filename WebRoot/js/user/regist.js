$(function(){
	/*得到所有的错误信息，循环遍历之，调用一个方法来确定是否显示错误信息 */
	$('.labelError').each(function(){
		showError($(this));
	});
	
	/*提交按钮图片变换*/
	$('#submit').hover(function(){
		//鼠标移开事件
		$(this).attr('src','../images/regist1.jpg');
	},function(){
		//鼠标移入事件
		$(this).attr('src','../images/regist2.jpg');
	});
	
	/*第一个输入框进入页面获得焦点并隐藏错误信息*/
	$('#loginname').focus();
	$('#loginnameError').css('display','none');
	
	/*当输入框失去焦点的时候触犯校验事件*/
	$('.input').blur(function(){
		//获得失去焦点输入框的id
		var id=$(this).attr('id');
		//将id凭借成需要校验的方法名
		var funname='validate'+id.substring(0,1).toUpperCase()+id.substring(1)+'();';
		//将字符串当做JS语句执行
		eval(funname);
	});
	
});

/*校验用户名*/
function validateLoginname(){
	var value=$('#loginname').val();
	//非空校验
	if(!value){
		//设置错误信息
		$('#loginnameError').text('用户名不能为空！');
		//显示
		showError($('#loginnameError'));
		//
		return false;
	}
	//长度校验
	if(value.length<2||value.length>20){
		//设置错误信息
		$('#loginnameError').text('用户名长度需在2~20之间！');
		//显示
		showError($('#loginnameError'));
		//
		return false;
	}
	//重复校验
	var result=false;
	$.ajax({
	    'url' : '../regist/validateLoginname.do',
	    'data':{'loginname':value},
	    'type' : 'GET',
	    'async':false,
	    'cache':false,
	    'dataType' : 'text',
	    'success' : function(msg) {
	    	if('Y'==msg){
	    		showError($('#loginnameError').text(''));
	    		result=true;
	    	}else{
	    		//设置错误信息
	    		$('#loginnameError').text('用户名已存在！');
	    		//显示
	    		showError($('#loginnameError'));
	    		result=false;
	    	}
	    },
	    'error' : function() {
	    	return false;
	    }
	   });
	return result;
}

/*校验密码*/
function validateLoginpass(){
	var value=$('#loginpass').val();
	//非空校验
	if(!value){
		//设置错误信息
		$('#loginpassError').text('密码不能为空！');
		//显示
		showError($('#loginpassError'));
		//
		return false;
	}
	//长度校验
	if(value.length<2||value.length>20){
		//设置错误信息
		$('#loginpassError').text('密码长度需在2~20之间！');
		//显示
		showError($('#loginpassError'));
		//
		return false;
	}
	//
	showError($('#loginpassError').text(''));
	return true;
}

/*校验确认密码*/
function validateReloginpass(){
	var value=$('#reloginpass').val();
	//非空校验
	if(!value){
		//设置错误信息
		$('#reloginpassError').text('确认密码不能为空！');
		//显示
		showError($('#reloginpassError'));
		//
		return false;
	}
	//长度校验
	if(value.length<2||value.length>20){
		//设置错误信息
		$('#reloginpassError').text('确认密码长度需在2~20之间！');
		//显示
		showError($('#reloginpassError'));
		//
		return false;
	}
	//密码输入重复校验
	if(value!=$('#loginpass').val()){
		//设置错误信息
		$('#reloginpassError').text('两次输入密码不正确！');
		//显示
		showError($('#reloginpassError'));
		//
		return false;
	}
	//
	showError($('#reloginpassError').text(''));
	return true;
}

/*校验邮箱*/
function validateEmail(){
	var value=$('#email').val();
	//非空校验
	if(!value){
		//设置错误信息
		$('#emailError').text('邮箱不能为空！');
		//显示
		showError($('#emailError'));
		//
		return false;
	}
	//格式校验
	if(!/^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*$/.test(value)){
		
		//设置错误信息
		$('#emailError').text('邮箱格式错误');
		showError($('#emailError'));
		//
		return false;
	}
	//
	showError($('#emailError').text(''));
	return true;
}

/*校验验证码*/
function validateVerifyCode(){
	//
	var value=$('#verifyCode').val();
	//验证是否正确
	var result=false;
	$.ajax({
		'url':'../regist/validateVCode.do',
		'data':{'vCode':value},
		'type':'POST',
		'dataType':'text',
		'async':false,
		'cache':false,
		'success':function(msg){
			if('N'== msg){
				$('#verifyCodeError').text('验证码错误！');
				showError($('#verifyCodeError'));
				result=false;
			}else{
				showError($('#verifyCodeError').text(''));
				result=true;
			}
		},
		'error':function(){}
	});
	
	return result;
	
}

/*表单提交验证*/
function registForm(){
	console.log('表单验证');
	console.log(validateLoginname());
	console.log(validateLoginpass());
	console.log(validateReloginpass());
	console.log(validateEmail());
	console.log(validateVerifyCode());
	return validateLoginname()&&validateLoginpass()&&validateReloginpass()&&validateEmail()&&validateVerifyCode();
}

/*判断错误信息元素内有无内容来决定显示与否*/
function showError(ele){
	//获取元素的内容
	var text=ele.text();
	if(!text){//如果没有内容
		ele.css('display','none');//隐藏元素
	}else{//如果有内容
		ele.css('display','');//显示元素
	}
}

/*验证码换一张*/
function changeVelidateCode(){
	//必须添加参数以欺骗浏览器是另一个请求
	$('#vCode').attr('src','../regist/verfiycode.do?t='+new Date().getTime());
}