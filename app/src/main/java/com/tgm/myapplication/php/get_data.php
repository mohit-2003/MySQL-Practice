// Getting Data from Server as json
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

$sql = "SELECT id, name, pass FROM details;";
//creating an statment with the query
$stmt = $conn->prepare($sql);

//executing that statment
$stmt->execute();

//binding results for that statment
$stmt->bind_result($id, $name, $pass);
 $data = array();
//looping through all the records
while($stmt->fetch()){
 
 //pushing fetched data in an array 
 $temp = [
 'id'=>$id,
 'name'=>$name,
 'pass'=>$pass
 ];
 //pushing the array inside the hero array 
 array_push($data, $temp);
}
echo json_encode($data);
mysqli_close($conn);
?>