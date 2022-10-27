<?php 

//write the query to get data from users table

$sql = "SELECT * FROM documents";
//execute the query
$servername = "localhost";
$username = "root"; // default username for localhost is root
$password = ""; // default password for localhost is empty
$dbname = "lab6"; // database name

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
$result = $conn->query($sql);

