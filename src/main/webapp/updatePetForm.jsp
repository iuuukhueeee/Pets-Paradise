<%@ page import="com.DTO.ShopDTO" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.DTO.ServiceDTO" %>
<%@ page import="com.DTO.UserDTO" %>
<%@ page import="com.DTO.PetDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <%
    UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
    PetDTO pet = (PetDTO) request.getAttribute("PET");
  %>

  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"/>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"/>
  <link rel="stylesheet" href="https://unpkg.com/flowbite@1.4.7/dist/flowbite.min.css"/>
  <link rel="stylesheet" href="./css/form.css"/>
  <link rel="preconnect" href="https://fonts.googleapis.com"/>
  <link rel="preconnect" href="https://fonts.gstatic.com"/>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com">
  <link
          href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;500&family=Raleway:wght@100;500&family=Roboto+Mono:wght@300&display=swap"
          rel="stylesheet">
  <link rel="shortcut icon" type="img/png" href="/img/paw-solid.svg"/>
  <title>Form</title>
</head>

<body>
<section class="form">
  <div class="container">
    <h1>Your pet's info</h1>
    <form action="MainController" method="POST" enctype="multipart/form-data">
      <input type="hidden" value="<%= pet.getPetID()%>" name="petID">
      <input type="hidden" value="<%= user.getUsername()%>" name="username">
      <div class="grid gap-6 mb-6 lg:grid-cols-2">
        <div>
          <label for="first_name"
                 class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300">Pet's
            name</label>
          <input type="text" id="animal_name" name="animalName" value="<%= pet.getAnimalName()%>"
                 class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                 placeholder="Lucky" required="">
        </div>
        <div>
          <label for="animal_age"
                 class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300">Pet Age
          </label>
          <input type="text" id="last_name" name="animalAge" value="<%= pet.getAnimalAge()%>"
                 class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                 placeholder="1" required="">
        </div>
        <div>
          <label for="description"
                 class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300">Pet
            description</label>
          <input type="text" id="description" name="animalDescription" value="<%= pet.getAnimalDescription()%>"
                 class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                 placeholder="Your pet's health">
        </div>
        <div>
          <label for="types"
                 class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-400">Select your pet's
            breed
          </label>
          <select id="types" name="animalType"
                  class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                  style="margin-bottom: 2rem;">
            <option selected="">Choose an option</option>
            <option value="Dog">Dog</option>
            <option value="Cat">Cat</option>
          </select>

        </div>

      </div>

      <!-- <div class="mb-6">
          <label for="password"
              class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300">Password</label>
          <input type="password" id="password"
              class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              placeholder="•••••••••" required="">
      </div> -->
      <div class="mb-6">

        <label class="block mb-2 text-sm font-medium text-gray-900 dark:text-gray-300"
               for="file_input">Upload file</label>
        <input
                class="block w-full text-sm text-gray-900 bg-gray-50 rounded-lg border border-gray-300 cursor-pointer dark:text-gray-400 focus:outline-none dark:bg-blue-600 dark:border-gray-600 dark:placeholder-gray-400"
                id="file_input" type="file" name="picture">


      </div>


      <button type="submit" name="action" value="UpdatePetInfo"
              class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
        Update Pet Info
      </button>
    </form>
  </div>
</section>
<script src="https://unpkg.com/flowbite@1.4.7/dist/flowbite.js"></script>

</body>

</html>