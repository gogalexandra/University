<?php

include "connect.php";

global $con;

session_start();

if(isset($_POST['url']))
    $url = $_POST['url'];
else
    echo "Error";
if(isset($_POST['category']))
    $category = $_POST['category'];
else
    echo "Error";
if(isset($_POST['description']))
    $description = $_POST['description'];
else
    echo "Error";
if(isset($_POST['urlId']))
    $urlId = $_POST['urlId'];
else
    echo "Error";

$query = "UPDATE `urls` SET `link`='$url',`description`='$description',`category`='$category' WHERE id=$urlId";
$result_set = $con->query($query);

if($result_set === TRUE) {
    echo "Record updated successfully";
} else {
    echo "Error";
}