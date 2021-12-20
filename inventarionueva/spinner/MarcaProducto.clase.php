<?php
    include 'Conexion.clase.php';
    class MarcaProducto extends Conexion{
        public function listar(){
            $sql = "SELECT marcaproducto.idMarcaProducto, marcaproducto.nombreMarca FROM marcaproducto";
            $sentencia = $this->dblink->prepare($sql);
            $sentencia->execute();            
            return $sentencia->fetchAll(PDO::FETCH_OBJ);
        }  
        }
?>