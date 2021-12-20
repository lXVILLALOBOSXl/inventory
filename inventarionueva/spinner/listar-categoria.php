<?php
    include 'CategoriaProducto.clase.php';
    $objCategoria = new CategoriaProducto();
    $resultado = $objCategoria->listar();
    $respuesta = array(
        "usuario"=>$resultado
    );
    echo json_encode($respuesta);   
?>