<?php
    include 'CantidadProducto.clase.php';
    $objCantidad = new CantidadProducto();
    $resultado = $objCantidad->listar();
    $respuesta = array(
        "usuario"=>$resultado
    );
    echo json_encode($respuesta);   
?>