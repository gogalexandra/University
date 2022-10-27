<?php
header('Access-Control-Allow-Origin: *');
header('Content-type: application/json');
    $sql = "SELECT * FROM documents";
//    //execute the query
    $servername = "localhost";
    $username = "root"; // default username for localhost is root
    $password = ""; // default password for localhost is empty
    $dbname = "lab6"; // database name
//
//    // Create connection
//    $conn = new mysqli($servername, $username, $password, $dbname);
//    $stmt = $conn->prepare($sql);
//    $result = $stmt->execute();
//    $result = $stmt->get_result();
//    return $result;

//    if ($result->num_rows > 0) {
//        //output data of each row
//        while ($row = $result->fetch_assoc()) {
//

    $connection = new mysqli($servername, $username, $password, $dbname);
    if ($connection->connect_error) {
        die("Connection failed: " . $connection->connect_error);
    }
    $getAllStmt = $connection->prepare(
        "SELECT * FROM documents"
    );
    $getAllStmt->execute();
    $result = $getAllStmt->get_result();

    if ($result->num_rows > 0) {
        $array = array();

        while($row = $result->fetch_assoc()) {
            $obj = array(
                'id' => $row['id'],
                'title' => $row['title'],
                'author' => $row['author'],
                'noOfPages' => $row['noOfPages'],
                'type' => $row['type'],
                'format' => $row['format'],
            );

            $json_obj = json_encode($obj);
            array_push($array, $obj);
        }

        echo json_encode($array);
    } else {
        echo "0 results";
    }

    $connection->close();

//    $echoArray = array();
//
//    if ($result->num_rows > 0) {
//        while ($row = mysqli_fetch_row($result)) {
//            $echoArray[] = $row;
//        }
//    } else {
//        echo "0 results";
//        return;
//    }
//
//    echo json_encode($echoArray);
    //echo json_encode($result);