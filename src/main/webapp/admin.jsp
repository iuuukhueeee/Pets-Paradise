<%@ page import="com.DTO.IncomeDTO" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"/>
    <link rel="shortcut icon" type="img/png" href="img/paw-solid.svg"/>
    <link rel="stylesheet" href="css/admin.css">
    <link rel="stylesheet" href="css/dropDownAdmin.css">
    <link
            href="https://fonts.googleapis.com/css2?family=Oswald:wght@200;300;500&family=Raleway:wght@100;500&family=Roboto+Mono:wght@300&display=swap"
            rel="stylesheet">

    <title>Admin</title>

</head>

<body>
  <div class="body">
    <div class="sidebar">
      <div class="LOGO">
        <img src="./img/paw-solid.svg" alt="" class="img-responsive" />
        <div style="
              font-size: 20px;
              font-weight: 900;
              color: #68a7ad;
              justify-content: center;
              padding-left: 16px;
            ">
          Pet's Paradise
        </div>
      </div>
      <div>
        <a href="admin" class="collapsible active">DashBoard</a>
      </div>
      <div>
        <a class="collapsible" href="AdminOrder">Order</a>
      </div>
      <div>
        <a href="AdminProduct" class="collapsible">Product</a>
      </div>
      <div>
        <a href="AdminService" class="collapsible">Service</a>
      </div>
      <div>
        <a href="AdminUser" class="collapsible">User</a>
      </div>
      <div>
        <a href="AdminBlog" class="collapsible">Blog</a>
      </div>
    </div>
    <div class="content">
      <div class="container">
        <div class="row height d-flex justify-content-center align-items-center graph">
          <div class="chart--container" style="position: relative; height: 40vh; width: 80vw">
            <canvas id="canvas"></canvas>
          </div>
        </div>
      </div>
    </div>
</div>
<select id="month" style="display: none">
    <%
        Map<String, IncomeDTO> map = (Map<String, IncomeDTO>) request.getAttribute("CHART");
        for (String month: map.keySet()) {
    %>
        <option value="<%=map.get(month).getTotal()%>"><%=map.get(month).getMonth()%></option>
    <%
        }
    %>
</select>

  <script src="./js/admin.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/chart.js@3.8.0/dist/chart.min.js"></script>
  <script
    src="https://cdn.jsdelivr.net/npm/chartjs-adapter-date-fns/dist/chartjs-adapter-date-fns.bundle.min.js"></script>
  <script src="./js/drawChart.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>