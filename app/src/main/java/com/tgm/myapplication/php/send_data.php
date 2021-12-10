// Sending Data to Server

<?php

//Define your host here.
$HostName = "localhost";

//Define your database username here.
$HostUser = "root";

//Define your database password here.
$HostPass = "";

//Define your database name here.
$DatabaseName = "test";

$conn = mysqli_connect($HostName, $HostUser, $HostPass, $DatabaseName);

if (!$conn) {
	echo 'YAY, Database not connected...';
	die();
}

$name = $_POST["name"]; 
$pass = $_POST["password"];

$sql = "INSERT INTO `details` VALUES (NULL, '$name', '$pass');";
$result = mysqli_query($conn, $sql);
if($result)
	echo "Data Inserted Successfully..";
else echo "Data Insertion Failed Successfully..";
mysqli_close($conn);

?>
