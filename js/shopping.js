
// $(document).each(function(){
// 	$('#addtocart').on('click',function(){
// 	  var button = $(this);
// 	  var cart = $('#cart');
// 	  var cartTotal = cart.attr('data-totalitems');
// 	  var newCartTotal = parseInt(cartTotal) + 1;
// 	  button.addClass('sendtocart');
// 	  setTimeout(function(){
// 		button.removeClass('sendtocart');
// 		cart.addClass('shake').attr('data-totalitems', newCartTotal);
// 		setTimeout(function(){
// 		  cart.removeClass('shake');
// 		},500)
// 	  },1000)
// 	})
// 	function display(){
// 		$.function()
// 	}
//   })
$(document).ready(function() {
  var cartCountValue = 0;
  var cartCount = $('.cart .count');
  $(cartCount).text(cartCountValue);

  $('.cart-btn').on('click', function() {
	var cartBtn = this;
	var cartCountPosition = $(cartCount).offset();
	var btnPosition = $(this).offset();
	var leftPos =
	  cartCountPosition.left < btnPosition.left
		? btnPosition.left - (btnPosition.left - cartCountPosition.left)
		: cartCountPosition.left;
	var topPos =
	  cartCountPosition.top < btnPosition.top
		? cartCountPosition.top
		: cartCountPosition.top;
	$(cartBtn)
	  .append("<span class='count'>1</span>");
	
	$(cartBtn).find(".count").each(function(i,count){
	  $(count).offset({
		left: leftPos,
		top: topPos
	  })
	  .animate(
		{
		  opacity: 0
		},
		800,
		function() {
		  $(this).remove();
		  cartCountValue++;
		  $(cartCount).text(cartCountValue);
		}
	  );
	}); 
  });
});