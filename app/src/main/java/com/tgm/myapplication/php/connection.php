/** simply checking for connection */

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

if ($conn) {
	echo "Successfully";
} else {
	echo 'YAY, Database not connected...';
}
 mysqli_close($conn);
?>