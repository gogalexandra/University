<?php
header('Access-Control-Allow-Origin: *');
header('Content-type: application/json');
//execute the query
$servername = "localhost";
$username = "root"; // default username for localhost is root
$password = ""; // default password for localhost is empty
$dbname = "lab6"; // database name

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

$json = file_get_contents('php://input');
$array = json_decode($json);

$title = $array->title;
$author = $array->author;
$noOfPages = (int)$array->noOfPages;
$type = $array->type;
$format = $array->format;

$stmt = $conn->prepare('INSERT INTO documents (title, author, noOfPages, type, format)
    VALUES (?, ?, ?, ?, ?)');

$stmt->bind_param('ssiss',  $title, $author, $noOfPages, $type, $format);

$stmt->execute();

$conn->close();