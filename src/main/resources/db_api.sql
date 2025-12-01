-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-12-2025 a las 03:55:25
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `db_api`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `brand`
--

CREATE TABLE `brand` (
  `id` binary(16) NOT NULL,
  `active` bit(1) NOT NULL,
  `brand_name` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `brand`
--

INSERT INTO `brand` (`id`, `active`, `brand_name`) VALUES
(0x14ba3c7e3e7c458e8b9d59859368ae37, b'1', 'Pokémon TCG'),
(0x270502a6e28743999c3b62edaab8fca0, b'0', 'Yu-Gi-Oh!'),
(0x6e7f8cf7d2ca4298964d8ddacf290cc9, b'1', 'Devir'),
(0xae1b4898409544558934023589e480a9, b'1', 'Magic: The Gathering'),
(0xbdd9fb3dc8ad44928c1cd25222d165fd, b'1', 'One Piece Card Game'),
(0xe7726e25f23947fd8ed97437bc0786dd, b'1', 'Dragonshield'),
(0xf201d9879a994b15975f80b49f00ecfb, b'1', 'Star Wars: Unlimited');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `category`
--

CREATE TABLE `category` (
  `id` binary(16) NOT NULL,
  `active` bit(1) NOT NULL,
  `category_name` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `category`
--

INSERT INTO `category` (`id`, `active`, `category_name`) VALUES
(0x133996960b98461896c1f14cc536f7b9, b'1', 'TCG'),
(0x68cdcadf14eb445e83f2a31bc82d33fb, b'1', 'Juegos De Mesa'),
(0xa0fe66b532b446d7b4cee888ee9efbcb, b'1', 'Accesorios');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `client_order`
--

CREATE TABLE `client_order` (
  `id` binary(16) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `full_name` varchar(100) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `shipping_address` varchar(100) NOT NULL,
  `shipping_city` varchar(50) NOT NULL,
  `shipping_zip` varchar(10) NOT NULL,
  `status` varchar(50) NOT NULL,
  `total_price` int(11) NOT NULL,
  `user_id` binary(16) DEFAULT NULL,
  `shipping_fee` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `client_order`
--

INSERT INTO `client_order` (`id`, `created_at`, `full_name`, `phone`, `shipping_address`, `shipping_city`, `shipping_zip`, `status`, `total_price`, `user_id`, `shipping_fee`) VALUES
(0x09fdcdddba324df6b8526fa3d42681a9, '2025-11-30 23:12:40.000000', 'admin test', '967519879', 'avenida chacabuco 1232-A', 'concepcion', '4030000', 'CANCELADO', 64970, 0x30915e48bc6847fa9c052569b31a2b00, 5000),
(0x17bd947c24914b0b8be843e4a3c75648, '2025-11-30 23:46:36.000000', 'diego melomastica', '967519879', 'avenida chacabuco 1232-A', 'concepcion', '4030000', 'PENDIENTE', 44990, 0xda2bb52bf505429a86908253a1e75c76, 5000),
(0x1b5d12298e384b89b5f517ab7dc1ca9b, '2025-11-29 15:33:34.000000', 'jose valdes', '967519879', 'avenida chacabuco 1232-A', 'concepcion', '4030000', 'PENDIENTE', 54990, 0x6eb21fb8613642e38700d531cf64bdba, 5000),
(0x316b7d5e25354325b308d313d0d72877, '2025-11-30 23:38:33.000000', 'diego melomastica', '967519879', 'avenida chacabuco 1232-A', 'concepcion', '4030000', 'PENDIENTE', 36980, 0xda2bb52bf505429a86908253a1e75c76, 5000),
(0x34328676a1b14954b460b9fc1c30c3a1, '2025-11-29 17:33:32.000000', 'jose valdes', '967519879', 'avenida chacabuco 1232-A', 'concepcion', '4030000', 'ENVIADO', 48980, 0x6eb21fb8613642e38700d531cf64bdba, 5000),
(0x752a5edef51240aea90de386655f3cbb, '2025-11-30 23:48:26.000000', 'diego melomastica', '967519879', 'avenida chacabuco 1232-A', 'concepcion', '4030000', 'PENDIENTE', 154990, 0xda2bb52bf505429a86908253a1e75c76, 5000),
(0xdbcd4592a3a147cb8fd97c5456d8bb7c, '2025-11-30 23:45:11.000000', 'diego melomastica', '967519879', 'avenida chacabuco 1232-A', 'concepcion', '4030000', 'PENDIENTE', 88970, 0xda2bb52bf505429a86908253a1e75c76, 5000),
(0xde292891f12f41dd823e02b96227e3af, '2025-11-29 16:02:21.000000', 'jose valdes', '967519879', 'avenida chacabuco 1232-A', 'concepcion', '4030000', 'ENVIADO', 18990, 0x6eb21fb8613642e38700d531cf64bdba, 5000),
(0xf00d3bccfcb4449eb409209b417a8b82, '2025-11-30 23:41:48.000000', 'diego melomastica', '967519879', 'avenida chacabuco 1232-A', 'concepcion', '4030000', 'ENVIADO', 5482550, 0xda2bb52bf505429a86908253a1e75c76, 5000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `order_item`
--

CREATE TABLE `order_item` (
  `id` binary(16) NOT NULL,
  `quantity` int(11) NOT NULL,
  `sub_total` int(11) NOT NULL,
  `unit_price` int(11) NOT NULL,
  `order_id` binary(16) DEFAULT NULL,
  `product_id` binary(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `order_item`
--

INSERT INTO `order_item` (`id`, `quantity`, `sub_total`, `unit_price`, `order_id`, `product_id`) VALUES
(0x18a840dee0474b5c9b3d3ab73195b5fb, 1, 39990, 39990, 0x17bd947c24914b0b8be843e4a3c75648, 0x85df650e93ae4282af1e1e78ed0900a7),
(0x26ea5c02b3004e6a89495c2d4555717a, 6, 299940, 49990, 0xf00d3bccfcb4449eb409209b417a8b82, 0x27e10a5a1a7247e0a2dd6eb5a5112a5a),
(0x2c5cdce4117e4980a3c0015134488406, 1, 39990, 39990, 0xdbcd4592a3a147cb8fd97c5456d8bb7c, 0x85df650e93ae4282af1e1e78ed0900a7),
(0x2f6804d8e0f54ea89723d11e3ee6799c, 16, 287840, 17990, 0xf00d3bccfcb4449eb409209b417a8b82, 0x9e457fcd8c314c819b6991b95594760f),
(0x4ff36aae2f204fa887d9ddd07e19a384, 23, 4599770, 199990, 0xf00d3bccfcb4449eb409209b417a8b82, 0xaf7f72fe033a4d8699ba6d7884f2f159),
(0x6713efb906154b75bb66794498d339f8, 1, 49990, 49990, 0x1b5d12298e384b89b5f517ab7dc1ca9b, 0x27e10a5a1a7247e0a2dd6eb5a5112a5a),
(0x706560a5e33045ef9cf80ab5e60fa69f, 1, 49990, 49990, 0x09fdcdddba324df6b8526fa3d42681a9, 0x27e10a5a1a7247e0a2dd6eb5a5112a5a),
(0x7668f8dc6fd84908a27ec1f88cb76295, 2, 290000, 145000, 0xf00d3bccfcb4449eb409209b417a8b82, 0x721a87a39e254d92801fee3f39630b2f),
(0x877a2bd8ea2b4ca3b2dcc0ec661150bc, 1, 13990, 13990, 0x316b7d5e25354325b308d313d0d72877, 0xcf07d0d097b7436290395c9215f668c7),
(0x9124d60f71af429bbd63d2c75011bed5, 2, 43980, 21990, 0x34328676a1b14954b460b9fc1c30c3a1, 0x46b3b8b0fb8a4df29243677d18282bbc),
(0x9d6e377616f64bc6b583b0f56b63d0fd, 1, 149990, 149990, 0x752a5edef51240aea90de386655f3cbb, 0xcc1f2bbbe98247e4b93abfd5da8b3db4),
(0xad57645d84ee4937b505f7724530d2de, 2, 9980, 4990, 0x09fdcdddba324df6b8526fa3d42681a9, 0x4a15a1bc245447ec906fbeb02baff27c),
(0xb2b57dc9fbc941c39f6b29718c8ed482, 1, 17990, 17990, 0x316b7d5e25354325b308d313d0d72877, 0x9e457fcd8c314c819b6991b95594760f),
(0xc9bf3b6537864c46901e9cfbf97566f0, 2, 43980, 21990, 0xdbcd4592a3a147cb8fd97c5456d8bb7c, 0x46b3b8b0fb8a4df29243677d18282bbc),
(0xd7d219e3ad2b414c83f86262eb0a6835, 1, 13990, 13990, 0xde292891f12f41dd823e02b96227e3af, 0x0f9dd48daac34f36a74a8fc6ddeca3f0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product`
--

CREATE TABLE `product` (
  `id` binary(16) NOT NULL,
  `active` bit(1) NOT NULL,
  `description` varchar(3000) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `max_player_number` int(11) DEFAULT NULL,
  `min_player_number` int(11) DEFAULT NULL,
  `name` varchar(200) NOT NULL,
  `price` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `stock` int(11) NOT NULL,
  `brand_id` binary(16) NOT NULL,
  `category_id` binary(16) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `product`
--

INSERT INTO `product` (`id`, `active`, `description`, `img_url`, `max_player_number`, `min_player_number`, `name`, `price`, `quantity`, `stock`, `brand_id`, `category_id`) VALUES
(0x0513ebddb67e4fa090d0bc0801c09405, b'0', 'asdfasdfas', 'https://devirinvestments.s3.eu-west-1.amazonaws.com/img/catalog/product/8436017220100-1200-frontflat.jpg', 4, 2, 'https://devirinvestments.s3.eu-west-1.amazonaws.com/img/catalog/product/8436017220100-1200-frontflat.jpg', 39990, 0, 67, 0x6e7f8cf7d2ca4298964d8ddacf290cc9, 0x68cdcadf14eb445e83f2a31bc82d33fb),
(0x0f9dd48daac34f36a74a8fc6ddeca3f0, b'1', 'Frente transparente y posterior según diseño.\n\nLos protectores mate son nuestra línea popular de Dragon Shield texturizado con un manejo superior. Una combinación perfecta de durabilidad y capacidad de barajar. La elección n.º 1 del profesor para protectores de cartas.', 'https://www.dragonshield.com/_next/image?url=https%3A%2F%2F97bc04b8bc9c8225faf2-4d11409729ec20784fbb81e5e6afe111.ssl.cf3.rackcdn.com%2Fbox_sleeve_flat-6UB8ktok.png&w=640&q=75', 0, 0, 'Protectores Standard Dragon Shield Dual Matte (Power)', 13990, 100, 1, 0xe7726e25f23947fd8ed97437bc0786dd, 0xa0fe66b532b446d7b4cee888ee9efbcb),
(0x27e10a5a1a7247e0a2dd6eb5a5112a5a, b'1', '¡La energía surge a medida que se despiertan nuevos poderes y se revelan peligrosas leyendas! Los primeros socios Meowscarada ex, Skeledirge ex y Quaquaval ex han evolucionado para subir al escenario con magia, canciones y bailes fascinantes. Mientras tanto, Forretress, Slowking y Dedenne brillan como Tera Pokémon ex, Chien-Pao ex, Ting-Lu ex y otros aportan fortalezas abrumadoras que requieren coraje para dominar. ¡Incluso Pikachu se une a la ex fiesta Pokémon en la expansión Pokémon TCG: Scarlet & Violet-Paldea Evolved!', 'https://www.antartica.cl/media/catalog/product/8/2/820650504235_1.jpg?quality=80&bg-color=255,255,255&fit=bounds&height=700&width=700&canvas=700:700', 0, 0, 'Elite Trainer Box de Paldea Evolved', 49990, 0, 0, 0x14ba3c7e3e7c458e8b9d59859368ae37, 0x133996960b98461896c1f14cc536f7b9),
(0x46b3b8b0fb8a4df29243677d18282bbc, b'1', 'Playmat Millerax: un panorama de locura como nunca antes habías visto. Este tapete de juego de la serie Signature captura a la perfección el estilo único del querido artista de MTG y fantasía Ian Miller, con todos sus intrincados detalles y su majestuosidad que desafía la realidad. Disfruta de la nueva textura de superficie suave como la seda. Junto con la base de goma antideslizante y los bordes cosidos de primera calidad, tu tapete de juego se mantendrá en su sitio y te acompañará en innumerables aventuras.Nuestro resistente tubo para obras de arte te permite guardarla y transportarla cómodamente, para que puedas llevarte tu alfombrilla de juego a todas tus misiones. Las alfombrillas de juego se extienden perfectamente, sin molestos pliegues ni arrugas, para que siempre estés listo para la batalla. La alfombrilla mide 61 × 35 cm cuando está extendida.', 'https://www.dragonshield.com/_next/image?url=https%3A%2F%2Fimages.cdn.europe-west1.gcp.commercetools.com%2Fe7c2ee64-8f38-4279-b057-5d3aeb215469%2FAT-20519-DS-PLAYMAT--_uSgk8O2.png&w=640&q=75', 0, 0, 'Playmat Signature Series - The Millerax', 21990, 0, 1, 0xe7726e25f23947fd8ed97437bc0786dd, 0xa0fe66b532b446d7b4cee888ee9efbcb),
(0x4a15a1bc245447ec906fbeb02baff27c, b'1', 'Double Shell es una caja de cubierta resistente con dos capas de protección en todos sus lados.\n\n• La tapa envuelve la caja para un cierre seguro o se puede configurar para que permanezca abierta para facilitar el acceso a las tarjetas.\n\n• Los cubos encajarán en el interior.\n\n• El Double Shell cabe dentro de cajas Nest grandes y Magic Carpets\n\n• Cada Double Shell viene con un divisor y tiene un campo de escritura para personalización.\n\n• Capacidad para aproximadamente 120 tarjetas de doble funda o 150 tarjetas de una sola funda', 'https://www.dragonshield.com/_next/image?url=https%3A%2F%2F97bc04b8bc9c8225faf2-4d11409729ec20784fbb81e5e6afe111.ssl.cf3.rackcdn.com%2FAT-30650-DOUBLE_SHEL-j280jUK9.png&w=640&q=75', 0, 0, 'Deckbox Double Shell - Blood Red', 4990, 0, 10, 0xe7726e25f23947fd8ed97437bc0786dd, 0xa0fe66b532b446d7b4cee888ee9efbcb),
(0x721a87a39e254d92801fee3f39630b2f, b'1', 'Experimenta la fusión única de tradición y tecnología futurista en Kamigawa. Esta caja de set boosters ofrece una experiencia de apertura optimizada con arte inspirador y cartas de alta rareza.', 'https://media.wizards.com/2022/images/daily/en_7nweybEBsy.png', 0, 0, 'Kamigawa: Neon Dynasty Set Booster Box', 145000, 0, 0, 0xae1b4898409544558934023589e480a9, 0x133996960b98461896c1f14cc536f7b9),
(0x85df650e93ae4282af1e1e78ed0900a7, b'1', 'catansito jijijiji', 'https://devirinvestments.s3.eu-west-1.amazonaws.com/img/catalog/product/8436017220100-1200-frontflat.jpg', 6, 2, 'Catan', 39990, 0, 65, 0x6e7f8cf7d2ca4298964d8ddacf290cc9, 0x68cdcadf14eb445e83f2a31bc82d33fb),
(0x9e457fcd8c314c819b6991b95594760f, b'1', '¡Entra a una galaxia de infinitas posibilidades en Star Wars™: Unlimited! En este fácil y rápido juego de cartas coleccionables, los jugadores se enfrentan en batallas llenas de icónicos personajes de Star Wars. En esta caja para 2 jugadores del primer set, Spark of Rebellion, podrás encontrar dos mazos completos, listos para jugar, para que puedas aprender a jugar con un amigo. ¡Los jugadores podrán proteger a sus aliados como Luke Skywalker o ir a la ofensiva como Darth Vader!', 'https://www.huntercardtcg.com/wp-content/uploads/2024/01/20231012103532_12556.jpg', 0, 0, 'Starter Deck de Spark of Rebellion', 17990, 0, 51, 0xf201d9879a994b15975f80b49f00ecfb, 0x133996960b98461896c1f14cc536f7b9),
(0xaf7f72fe033a4d8699ba6d7884f2f159, b'1', '¡Hazte con el poder del pasado! ¡Celebra las 27 colecciones y los 30 años de juegos que forman parte de la historia de Magic en Dominaria!', 'https://media.wizards.com/2022/images/daily/sp_Cs3FsIuegs.png', 0, 0, 'Caja de Sobres de Dominaria Remastered', 199990, 0, 0, 0xae1b4898409544558934023589e480a9, 0x133996960b98461896c1f14cc536f7b9),
(0xcc1f2bbbe98247e4b93abfd5da8b3db4, b'1', '¡Ya están disponible los display de sobres de refuerzo de la sexta edición de One Piece Card Game. No te quedes sin ellos. Unidades limitadas.', 'https://aogeekstore.cl/cdn/shop/files/op6boxfix_480x_bfa9eb19-599e-47f2-8f6c-56474307f419.webp?v=1718046871', 0, 0, 'Display de Sobres de Wings of the Captain', 149990, 0, 2, 0xbdd9fb3dc8ad44928c1cd25222d165fd, 0x133996960b98461896c1f14cc536f7b9),
(0xcf07d0d097b7436290395c9215f668c7, b'1', 'Frente transparente y posterior según diseño.\n\nLos protectores mate son nuestra línea popular de Dragon Shield texturizado con un manejo superior. Una combinación perfecta de durabilidad y capacidad de barajar.', 'https://www.dragonshield.com/_next/image?url=https%3A%2F%2F97bc04b8bc9c8225faf2-4d11409729ec20784fbb81e5e6afe111.ssl.cf3.rackcdn.com%2Fbox_sleeve_flat-1H7mj65q.png&w=640&q=75', 0, 0, 'Protectores Standard Dragon Shield Dual Matte (Soul)', 13990, 100, 18, 0xe7726e25f23947fd8ed97437bc0786dd, 0xa0fe66b532b446d7b4cee888ee9efbcb),
(0xd42a4269d3e74ed393b7b909a8eaf407, b'1', 'Embárcate en una aventura de Dungeons & Dragons con esta caja de booster coleccionista. Contiene cartas con tratamiento especial, foils espectaculares y criaturas legendarias únicas del universo de Faerûn.', 'https://www.magicsur.cl/22550-thickbox_default/mtg-caja-de-collector-booster-commander-legends-battle-for-baldurs-gate.jpg', 0, 0, 'Commander Legends: Battle for Baldur\'s Gate Collector Booster Box', 185000, 0, 3, 0xae1b4898409544558934023589e480a9, 0x133996960b98461896c1f14cc536f7b9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `role`
--

CREATE TABLE `role` (
  `id` binary(16) NOT NULL,
  `active` bit(1) NOT NULL,
  `role_name` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `role`
--

INSERT INTO `role` (`id`, `active`, `role_name`) VALUES
(0x816bf18fb25b4e44af8ba4d761f4ebae, b'1', 'admin'),
(0x9a2662e7bc0b4a96885e917fb6bbcfb9, b'0', 'jOSE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_table`
--

CREATE TABLE `user_table` (
  `id` binary(16) NOT NULL,
  `active` bit(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `is_duoc` bit(1) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `name` varchar(150) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `role_id` binary(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `user_table`
--

INSERT INTO `user_table` (`id`, `active`, `email`, `is_duoc`, `last_name`, `name`, `password`, `phone_number`, `role_id`) VALUES
(0x30915e48bc6847fa9c052569b31a2b00, b'1', 'admin@example.com', b'0', 'test', 'admin', '{bcrypt}$2a$10$bpPyzj5ulqfYUm6hNIlvAueP5KTRpwGrWKGcVOfSm0QoQAKF19lUW', '', 0x816bf18fb25b4e44af8ba4d761f4ebae),
(0x6eb21fb8613642e38700d531cf64bdba, b'1', 'josealvarado.valdes98@gmail.com', b'0', 'valdes', 'jose', '{bcrypt}$2a$10$mZZrXhv/KiBVVN7AgoD23.Jc5ZBg3wy5SgYUUERFXQZ3/3lspTNke', NULL, NULL),
(0x7b8b873d1b9345b782ab6df193813b1c, b'1', 'josevaldes@gmail.com', b'0', 'soe', 'nove', '{bcrypt}$2a$10$S94L1YNo6DeXfWe53yDu0uif70RJIQunTByflbj5ZVeFEbCSoCpdi', NULL, NULL),
(0xda2bb52bf505429a86908253a1e75c76, b'1', 'diegomelomastica@duocuc.cl', b'0', 'melomastica', 'diego', '{bcrypt}$2a$10$dOupAREpvXf.YK1IRcamA.WYhKy/zmICB39b.gHob.Q5Ina1HEq/q', '967519879', 0x816bf18fb25b4e44af8ba4d761f4ebae);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKhsu7w3m7wxvplg49ip7g0v5rr` (`brand_name`);

--
-- Indices de la tabla `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKlroeo5fvfdeg4hpicn4lw7x9b` (`category_name`);

--
-- Indices de la tabla `client_order`
--
ALTER TABLE `client_order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5ccqx39954q5e3325e1sov8d6` (`user_id`);

--
-- Indices de la tabla `order_item`
--
ALTER TABLE `order_item`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9dm854t3ybtcsv86ro5lrin21` (`order_id`),
  ADD KEY `FK551losx9j75ss5d6bfsqvijna` (`product_id`);

--
-- Indices de la tabla `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKjmivyxk9rmgysrmsqw15lqr5b` (`name`),
  ADD UNIQUE KEY `UKq2n3melweyrl5d4rqkg7pq6ra` (`description`) USING HASH,
  ADD KEY `FKs6cydsualtsrprvlf2bb3lcam` (`brand_id`),
  ADD KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`);

--
-- Indices de la tabla `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKiubw515ff0ugtm28p8g3myt0h` (`role_name`);

--
-- Indices de la tabla `user_table`
--
ALTER TABLE `user_table`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKrlj7w92dy7h4d255rbat4ovcu` (`role_id`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `client_order`
--
ALTER TABLE `client_order`
  ADD CONSTRAINT `FK5ccqx39954q5e3325e1sov8d6` FOREIGN KEY (`user_id`) REFERENCES `user_table` (`id`);

--
-- Filtros para la tabla `order_item`
--
ALTER TABLE `order_item`
  ADD CONSTRAINT `FK551losx9j75ss5d6bfsqvijna` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FK9dm854t3ybtcsv86ro5lrin21` FOREIGN KEY (`order_id`) REFERENCES `client_order` (`id`);

--
-- Filtros para la tabla `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `FKs6cydsualtsrprvlf2bb3lcam` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`);

--
-- Filtros para la tabla `user_table`
--
ALTER TABLE `user_table`
  ADD CONSTRAINT `FKrlj7w92dy7h4d255rbat4ovcu` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
