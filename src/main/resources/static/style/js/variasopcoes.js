function myfun(){
				var a = document.getElementsByName('chk');	
				var newvar = 0;
				var count;
				
				for (count = 0; count < a.lenght; count++){
					if (a[count].checked == true){
						newvar = newvar +  1;
					}
					if (newvar > 2){
						document.getElementById('notvalid').innerHTML = "Selecione no máximo duas categorias"
						return false;	
					}
				}
			}