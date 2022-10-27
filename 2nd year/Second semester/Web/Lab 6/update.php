<?php
header('Access-Control-Allow-Headers: Access-Control-Allow-Origin, Content-Type');
header('Access-Control-Allow-Origin: *');
header('Content-Type: application/json, charset=utf-8');


$servername = "localhost";
$username = "root";
$password = "";
$dbname = "lab6";

$conn = new mysqli($servername, $username, $password, $dbname);

$json = file_get_contents('php://input');
$array = json_decode($json);

$id = $array->id;
$title = $array->title;
$author = $array->author;
$noOfPages = $array->noOfPages;
$type = $array->type;
$format = $array->format;

$stmt = $conn->prepare("UPDATE documents SET  title=?,
                                                    author=?,
                                                    noOfPages=?,
                                                    type=?,
                                                    format=?
                             WHERE id=?");
$stmt->bind_param('ssissi', $title, $author, $noOfPages, $type, $format, $id);
$stmt->execute();

$conn->close();


?>
