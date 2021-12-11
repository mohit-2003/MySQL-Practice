// updating data to server according to id
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
if($_SERVER['REQUEST_METHOD']=='POST'){
	$id = $_POST['id'];
	$name = $_POST['name'];
	$pass = $_POST['password'];
}

$sql = "UPDATE details SET name = '$name', pass = '$pass' WHERE id = '$id'";

if($conn->query($sql) === TRUE)
	echo "Data Updated Successfully...";
else
	echo "Successfully Failed to Update Data !!";

mysqli_close($conn);
?>