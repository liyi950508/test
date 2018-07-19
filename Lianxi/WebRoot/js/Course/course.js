//window.onload = function initAll(){
//	var menu = document.getElementById("li");
//	var sub = document.getElementById("sub");
//	var subli = document.getElementById("sub").getElementsByTagName("li");
//	menu.onmouseover = over;
//	sub.onmouseover = over;
//	menu.onmouseout = out;
//	subli.onmouseout = out;
//	function over() {
//		for(var i=0;i<subli.length;i++){
//			subli[i].style.display = "block";
//		}
//	}
//	function out(){
//		for(var i=0;i<subli.length;i++){
//			subli[i].style.display="none";
//		}
//	}
//}

function show(li) {
	var ul = li.getElementsByTagName("ul")[0];
	ul.style.display = "block";
}

function hide(li) {
	var ul = li.getElementsByTagName("ul")[0];
	ul.style.display = "none";
}

//function getByClass(obj, clname){
//	var aLi=obj.getElementsByTagName('*');    
//    var result=[];                         
//                      
//    for(var i=0;i<aLi.length;i++){          
//        if(aLi[i].className==clname){      
//            result.push(aLi[i]);            
//        }  
//    }  
//    return result;                           
//}
//window.onload = function() {
//	var oBox = document.getElementById('box'); 
//	var aBtn = getByClass(oBox, 'ul-li');
//	for(var i = 0; i < aBtn.length; i++) { 
//		aBtn[i].onmouseover = function() { 
//			this.children[1].style.display = 'block'; 
//		};
//		aBtn[i].onmouseout = function() {
//			this.children[1].style.display = 'none'; 
//		};
//	}
//};
