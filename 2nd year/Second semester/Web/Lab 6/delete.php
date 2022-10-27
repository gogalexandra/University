<?php
header('Access-Control-Allow-Origin: *');
header('Content-type: application/json');
    // Execute the query
    $servername = "localhost";
    $username = "root"; // default username for localhost is root
    $password = ""; // default password for localhost is empty
    $dbname = "lab6"; // database name

    $json = file_get_contents('php://input');
    $array = json_decode($json);
    $id = (int) $array->id;

    $conn = new mysqli($servername, $username, $password, $dbname);

    $stmt = $conn->prepare('DELETE FROM documents WHERE id=?');
    $stmt->bind_param('i', $id);


    // $sql = "DELETE FROM destinations
    //   where id=$id
    // ";

    if ($stmt->execute() === TRUE) {
        echo "Document deleted successfully";
    } else {
        echo "Error: " . "<br>" . $conn->error;
    }

    $conn->close();


//    $conn = new mysqli($servername, $username, $password, $dbname);
//    $sql =  $conn->prepare("DELETE FROM `documents` WHERE id =?");
//    $sql->bind_param("i", $id);
//
//    $result = $sql->execute();
////    $result = $sql->get_result();
//
//
//    if ($result) {
//        $result = array('status' => true, 'message' => 'Record deleted successfully.');
//    }else{
//        $result = array('status' => true, 'message' => $conn->error);
//    }
//
//    echo json_encode($result);exit();
