<?php 
include "config.php";

if (isset($_GET['get_users'])) {
    $sql = "SELECT * FROM documents";
    //execute the query
    $servername = "localhost";
    $username = "root"; // default username for localhost is root
    $password = ""; // default password for localhost is empty
    $dbname = "lab6"; // database name

// Create connection
    $conn = new mysqli($servername, $username, $password, $dbname);
    $result = $conn->query($sql);
    if ($result->num_rows > 0) {
        //output data of each row
        while ($row = $result->fetch_assoc()) {
    ?>

    <tr>
        <td><?php echo $row['id']; ?></td>
        <td><?php echo $row['title']; ?></td>
        <td><?php echo $row['author']; ?></td>
        <td><?php echo $row['noOfPages']; ?></td>
        <td><?php echo $row['type']; ?></td>
        <td><?php echo $row['format']; ?></td>
        <td><button class="btn btn-info" onclick="edit_record(<?php echo $row['id']; ?>)" >Edit</button>&nbsp;<button class="btn btn-danger"  onclick="delete_record(<?php echo $row['id']; ?>)" >Delete</button></td>
    </tr>   
                
    <?php
        }
    }
}
    
if (isset($_POST['title'])) {
    $id             = $_POST['id'];
    $title          = $_POST['title'];
    $author         = $_POST['author'];
    $noOfPages      = $_POST['noOfPages'];
    $type           = $_POST['type'];
    $format         = $_POST['format'];
    if ($title == '') {
        $sql = "INSERT INTO `documents`(`title`, `author`, `noOfPages`, `type`, `format`) VALUES ('$title','$author','$noOfPages','$type','$format')";
        $message = "Record created successfully.";
    }else{
        // write the update query
        $sql = "UPDATE `documents` SET `title`='$title',`author`='$author',`noOfPages`='$noOfPages',`type`='$type',`format`='$format' WHERE `id`='$id'";
        $message = "Record upated successfully.";
    }
    // execute the query
    $result = $conn->query($sql);

    if ($result) {
        $response = array('status' => true, 'message' => $message);
    }else{
        $response = array('status' => false, 'message' => $conn->error);
    }

    echo json_encode($response);exit();
}

if (isset($_GET['delete_id'])) {
	$id = $_GET['delete_id'];

	// write delete query
	$sql = "DELETE FROM `documents` WHERE `id`='$id'";

	// Execute the query

	$result = $conn->query($sql);

	if ($result) {
        $result = array('status' => true, 'message' => 'Record deleted successfully.');
	}else{
        $result = array('status' => true, 'message' => $conn->error);
	}

    echo json_encode($result);exit();
}

if (isset($_GET['edit_id'])) {
	$id = $_GET['edit_id'];

	// write SQL to get user data
	$sql = "SELECT * FROM `documents` WHERE `id`='$id'";

	//Execute the sql
	$result = $conn->query($sql);

	if ($result->num_rows > 0) {
		$row = $result->fetch_assoc();
		$response = array('status' => true, 'data' => $row);
	}else{
		$response = array('status' => false, 'data' => $conn->error);
	}
	echo json_encode($response);exit();
} 

?>