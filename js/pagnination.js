console.log("hello");
const ulTag = document.querySelector("ul");
function elememt(totalPage, page) {
  let liTag = "";
  if (page > 1) {
    liTag += `  <li>
	<a href="#"
		class="py-2 px-3 ml-0 leading-tight text-gray-500 bg-white rounded-l-lg border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 ">Previous</a>
</li>`;
  } else if (page < 1) {
    liTag += `<li>
	<a
	  href="#"
	  class="py-2 px-3 leading-tight text-gray-500 bg-white rounded-r-lg border border-gray-300 hover:bg-gray-100 hover:text-gray-700 dark:bg-gray-800 dark:border-gray-700 dark:text-gray-400 dark:hover:bg-gray-700 dark:hover:text-white"
	>
	  Next
	</a>
  </li>;`;
    
  }
  ulTag.innerHTML = liTag;
}
elememt(20, 4);
