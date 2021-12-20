<?php
    include 'Conexion.clase.php';
    class NombreProducto extends Conexion{
        public function listar(){
            $sql = "SELECT tipoproducto.idTipoProducto, tipoproducto.nombreProducto FROM tipoproducto";
            $sentencia = $this->dblink->prepare($sql);
            $sentencia->execute();            
            return $sentencia->fetchAll(PDO::FETCH_OBJ);
        }  
        }
?>