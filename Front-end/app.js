$("#btn-submit").click(function () {
  event.preventDefault();

  let name = $("#name").val();
  let email = $("#email").val();
  let city = $("#city").val();
  let level = $("#level").val();

  // Create an object for accumulate data
  const studentData = {
    name: name,
    email: email,
    city: city,
    level: level,
  };
  console.log(studentData);

  // Create JSON
  const studentJSON = JSON.stringify(studentData);
  console.log(studentJSON);

  // Introduce AJAX 
  const http = new XMLHttpRequest(); // this object have a no.of properties mentioning the request
  http.onreadystatechange = () => {
    if (http.readyState == 4) {
      if (http.status == 200) {
        var responseTextJSON = JSON.stringify(http.responseText)
        console.log(responseTextJSON);
      } else {
        console.error("Failed");
        console.error("Status" + http.status);
        console.error("Ready State" + http.readyState);
      }
    } else {
      console.error("Ready State" + http.readyState);
    }
  };
  http.open("POST", "http://localhost:8080/app/student", true);
  http.setRequestHeader("Content-Type", "application/json");
  http.send(studentJSON);
});
