// deleting data from server according to id
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
}

$sql = "DELETE FROM details WHERE id = '$id'";

if($conn->query($sql) === TRUE)
	echo "Data Deleted Successfully...";
else
	echo "Successfully Failed to Delete Data !!";

mysqli_close($conn);
?>