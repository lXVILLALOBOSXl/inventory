-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-12-2020 a las 19:05:23
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `inventario`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAllProducts` ()  BEGIN
	SELECT *  FROM producto;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `reiniciar` ()  BEGIN
	SET @count = 0;
    UPDATE producto SET producto.codigoProducto = @count:= @count + 1;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cantidadproducto`
--

CREATE TABLE `cantidadproducto` (
  `idCantidadProducto` int(10) NOT NULL,
  `cantidad` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cantidadproducto`
--

INSERT INTO `cantidadproducto` (`idCantidadProducto`, `cantidad`) VALUES
(1, 0),
(2, 2),
(3, 200);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoriaproducto`
--

CREATE TABLE `categoriaproducto` (
  `idCategoriaProducto` int(10) NOT NULL,
  `categoria` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `categoriaproducto`
--

INSERT INTO `categoriaproducto` (`idCategoriaProducto`, `categoria`) VALUES
(1, '-Seleccione un valor-'),
(2, 'Acido reactivo'),
(3, 'Acidos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marcaproducto`
--

CREATE TABLE `marcaproducto` (
  `idMarcaProducto` int(10) NOT NULL,
  `nombreMarca` varchar(100) NOT NULL,
  `paisMarca` varchar(100) NOT NULL,
  `sitioWebMarca` varchar(100) NOT NULL,
  `telefonoMarca` int(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `marcaproducto`
--

INSERT INTO `marcaproducto` (`idMarcaProducto`, `nombreMarca`, `paisMarca`, `sitioWebMarca`, `telefonoMarca`) VALUES
(1, '-Seleccione un valor-', '', '', 0),
(2, 'Jalmek', 'Mexico', 'jalmex.com', 123456),
(3, 'Hycel', 'Mexico', 'Hycel.com', 11223344);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `codigoProducto` int(10) NOT NULL,
  `imagenProducto` text DEFAULT NULL,
  `piezasProducto` int(10) NOT NULL,
  `precioProducto` decimal(12,2) NOT NULL,
  `idTipoProducto` int(10) NOT NULL,
  `idCategoriaProducto` int(10) NOT NULL,
  `idMarcaProducto` int(10) NOT NULL,
  `idCantidadProducto` int(10) NOT NULL,
  `idUnidadProducto` int(10) NOT NULL,
  `codigoInterno` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`codigoProducto`, `imagenProducto`, `piezasProducto`, `precioProducto`, `idTipoProducto`, `idCategoriaProducto`, `idMarcaProducto`, `idCantidadProducto`, `idUnidadProducto`, `codigoInterno`) VALUES
(1, 'NULL', 20, '240.00', 2, 2, 2, 2, 2, 'GDK');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipoproducto`
--

CREATE TABLE `tipoproducto` (
  `idTipoProducto` int(10) NOT NULL,
  `nombreProducto` varchar(100) NOT NULL,
  `descripcionProducto` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `tipoproducto`
--

INSERT INTO `tipoproducto` (`idTipoProducto`, `nombreProducto`, `descripcionProducto`) VALUES
(1, '-Seleccione un valor-', ''),
(2, 'Acido absorbico', 'Empleado en procesos quimicos'),
(3, 'Acido Citrico', 'Funciona como vitamina c');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `unidadproducto`
--

CREATE TABLE `unidadproducto` (
  `idUnidadProducto` int(10) NOT NULL,
  `unidad` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `unidadproducto`
--

INSERT INTO `unidadproducto` (`idUnidadProducto`, `unidad`) VALUES
(1, '-Seleccione un valor-'),
(2, 'Litros'),
(3, 'Mililitros');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `nombreUsuario` varchar(100) NOT NULL,
  `apellidoPaternoUsuario` varchar(100) NOT NULL,
  `apellidoMaternoUsuario` varchar(100) NOT NULL,
  `telefonoUsuario` varchar(15) NOT NULL,
  `correoUsuario` varchar(100) NOT NULL,
  `contrasenaUsuario` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`nombreUsuario`, `apellidoPaternoUsuario`, `apellidoMaternoUsuario`, `telefonoUsuario`, `correoUsuario`, `contrasenaUsuario`) VALUES
('Luis ', 'Villalobos', 'Rivera', '3313506904', 'luisvi', '123');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cantidadproducto`
--
ALTER TABLE `cantidadproducto`
  ADD PRIMARY KEY (`idCantidadProducto`);

--
-- Indices de la tabla `categoriaproducto`
--
ALTER TABLE `categoriaproducto`
  ADD PRIMARY KEY (`idCategoriaProducto`);

--
-- Indices de la tabla `marcaproducto`
--
ALTER TABLE `marcaproducto`
  ADD PRIMARY KEY (`idMarcaProducto`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`codigoProducto`),
  ADD KEY `idTipoProducto` (`idTipoProducto`),
  ADD KEY `idCategoriaProducto` (`idCategoriaProducto`),
  ADD KEY `idCantidadProducto` (`idCantidadProducto`),
  ADD KEY `idUnidadProducto` (`idUnidadProducto`),
  ADD KEY `idMarcaProducto` (`idMarcaProducto`);

--
-- Indices de la tabla `tipoproducto`
--
ALTER TABLE `tipoproducto`
  ADD PRIMARY KEY (`idTipoProducto`);

--
-- Indices de la tabla `unidadproducto`
--
ALTER TABLE `unidadproducto`
  ADD PRIMARY KEY (`idUnidadProducto`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`correoUsuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cantidadproducto`
--
ALTER TABLE `cantidadproducto`
  MODIFY `idCantidadProducto` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `categoriaproducto`
--
ALTER TABLE `categoriaproducto`
  MODIFY `idCategoriaProducto` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `marcaproducto`
--
ALTER TABLE `marcaproducto`
  MODIFY `idMarcaProducto` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `codigoProducto` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `tipoproducto`
--
ALTER TABLE `tipoproducto`
  MODIFY `idTipoProducto` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `unidadproducto`
--
ALTER TABLE `unidadproducto`
  MODIFY `idUnidadProducto` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`idTipoProducto`) REFERENCES `tipoproducto` (`idTipoProducto`),
  ADD CONSTRAINT `producto_ibfk_2` FOREIGN KEY (`idCategoriaProducto`) REFERENCES `categoriaproducto` (`idCategoriaProducto`),
  ADD CONSTRAINT `producto_ibfk_3` FOREIGN KEY (`idCantidadProducto`) REFERENCES `cantidadproducto` (`idCantidadProducto`),
  ADD CONSTRAINT `producto_ibfk_4` FOREIGN KEY (`idUnidadProducto`) REFERENCES `unidadproducto` (`idUnidadProducto`),
  ADD CONSTRAINT `producto_ibfk_5` FOREIGN KEY (`idMarcaProducto`) REFERENCES `marcaproducto` (`idMarcaProducto`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
