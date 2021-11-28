-- MySQL dump 10.13  Distrib 8.0.27, for macos11.6 (x86_64)
--
-- Host: 127.0.0.1    Database: sampple
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `phones`
--

DROP TABLE IF EXISTS `phones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `model` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `brand` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `color` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `storage` int DEFAULT NULL,
  `display_size` float DEFAULT NULL,
  `url_image` varchar(2048) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phones`
--

LOCK TABLES `phones` WRITE;
/*!40000 ALTER TABLE `phones` DISABLE KEYS */;
INSERT INTO `phones` VALUES (1,'iPhone 8','Apple','Red',64,4.7,'image url'),(2,'iPhone X','Apple','Red',64,4.7,'image url'),(3,'iPhone XS','Apple','Red',64,4.7,'image url'),(4,'iPhone XS MAX','Apple','Red',64,4.7,'image url'),(5,'iPhone 8','Apple','Unknown',64,4.7,'https://i.ebayimg.com/thumbs/images/g/znAAAOSweNJhOJhV/s-l300.webp'),(6,'iPhone 8','Apple','Black',64,4.7,'https://i.ebayimg.com/thumbs/images/g/iV0AAOSw6cJgrjRt/s-l300.webp'),(7,'iPhone 8','Apple','Black',128,4.7,'https://ir.ebaystatic.com/cr/v/c1/s_1x2.gif'),(8,'iPhone 8','Apple','Gold',64,4.7,'https://ir.ebaystatic.com/cr/v/c1/s_1x2.gif'),(9,'iPhone 8','Apple','Gold',256,4.7,'https://ir.ebaystatic.com/cr/v/c1/s_1x2.gif'),(10,'iPhone 8','Apple','Gold',128,4.7,'https://ir.ebaystatic.com/cr/v/c1/s_1x2.gif'),(11,'iPhone 8','Apple','Space Grey;',64,4.7,'https://i.ebayimg.com/thumbs/images/g/z9kAAOSwF7hhoM0p/s-l300.webp'),(12,'iPhone 8','Apple','Silver',64,4.7,'https://i.ebayimg.com/thumbs/images/g/SssAAOSwlSRf9WK-/s-l300.webp'),(13,'iPhone 8','Apple','Unknown',128,4.7,'https://ir.ebaystatic.com/cr/v/c1/s_1x2.gif'),(14,'iPhone 8','Apple','Red',256,4.7,'https://ir.ebaystatic.com/cr/v/c1/s_1x2.gif'),(15,'iPhone 8','Apple','Space Grey;',256,4.7,'https://ir.ebaystatic.com/cr/v/c1/s_1x2.gif'),(16,'iPhone 8','Apple','Unknown',256,4.7,'https://ir.ebaystatic.com/cr/v/c1/s_1x2.gif');
/*!40000 ALTER TABLE `phones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `phone_id` int DEFAULT '0',
  `url` varchar(2048) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `name` varchar(2048) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK__phones` (`phone_id`),
  CONSTRAINT `FK__phones` FOREIGN KEY (`phone_id`) REFERENCES `phones` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,5,'https://www.ebay.co.uk/itm/363508074020',214.99,'Brand New Apple iPhone 8 64GB 256GB Unlocked - All Colours - With Original Box'),(2,5,'https://www.ebay.co.uk/itm/124764453212',199.99,'Brand New Apple iPhone 8 - 64GB 256GB Unlocked - Sealed in Original Box'),(3,5,'https://www.ebay.co.uk/itm/165094231031',183.99,'🔥NEW sealed box Apple iPhone 8 64GB/256GB Smartphone Mobile Unlocked UK Stock🔥'),(4,5,'https://www.ebay.co.uk/p/27033738501',219.00,'Brand New Apple iPhone 8 64GB Unlocked With Original Box'),(5,5,'https://www.ebay.co.uk/p/238941601',214.99,'New Apple iPhone 8 64GB 256GB Unlocked - 12 Months Apple Warranty - Original Box'),(6,5,'https://www.ebay.co.uk/p/7032538571',215.00,'iphone 8 64gb unlocked'),(7,6,'https://www.ebay.co.uk/itm/133942478819',209.99,'*Sale* New Apple iPhone 8 64GB Phone in Black with 1 year manufacture guarantee'),(8,5,'https://www.ebay.co.uk/p/17037803402',174.88,'NEW SEALED Apple iPhone 8 64GB 256GB Unlocked Smartphone 1Year Warranty WITH BOX'),(9,7,'https://www.ebay.co.uk/p/21034268538',184.88,'New 128GB 256GB Apple iPhone 8 UNLOCKED Smartphone SIM Free Silver/Black/Gold'),(10,8,'https://www.ebay.co.uk/p/239054480',239.99,'NEW Apple iPhone 8 64GB 256GB ⚫⚪🟡🔴 🔓GSM Unlocked ✅ Gold ✅ Grey ✅ Silver ✅ Red'),(11,9,'https://www.ebay.co.uk/itm/224363076314',270.00,'Brand New Apple iPhone 8 256 GB Gold SIM-Free Smartphone With Accessories 12MP'),(12,10,'https://www.ebay.co.uk/p/9034259869',270.00,'New Apple iPhone 8 128GB GOLD SIM-Free Smartphone With Accessories 12MP'),(13,8,'https://www.ebay.co.uk/p/15042894800',199.00,'NEW Apple iPhone 8 64GB | 256GB (GSM UNLOCKED) GREY | SILVER | GOLD | RED'),(14,9,'https://www.ebay.co.uk/p/16021529010',184.88,'New 256GB Apple iPhone 8 UNLOCKED Smartphone SIM Free Silver/Space Grey/Gold'),(15,5,'https://www.ebay.co.uk/p/239068704',224.00,'Apple iPhone 8 64GB 256GB Fully Unlocked - Sealed in Original Box - Lowest Price'),(16,5,'https://www.ebay.co.uk/itm/154094204322',374.05,'Original Apple iPhone 8 4.7 inch Hexa Core 2GB RAM 64GB ROM 12MP & 7MP Camera 18'),(17,6,'https://www.ebay.co.uk/p/26036492614',186.88,'New Apple iPhone 8/8 Plus 128GB 64/256GB Unlocked Black Gold Silver Red Original'),(18,12,'https://www.ebay.co.uk/p/239165318',208.00,'NEW Apple iPhone 8 64GB | 256GB (UNLOCKED) Gray ║ Silver ║ RED || SEALED'),(19,8,'https://www.ebay.co.uk/itm/184916945703',186.88,'New Apple iPhone 8 128/64GB 256GB Unlocked - Grey Gold Silver Red 12M Warranty'),(20,8,'https://www.ebay.co.uk/p/239009722',210.00,'Apple iPhone 8 4.7 inch 64GB 2 GB RAM (Unlocked) Smartphone - Rose Gold'),(21,11,'https://www.ebay.co.uk/p/239099297',726.73,'Brand new Sealed Apple iPhone 8 - 64GB - Space Grey Network Unlocked SMARTPHONE'),(22,1,'https://www.ebay.co.uk/p/27032604865',209.99,'*Sale* New Apple iPhone 8 64GB Phone in Red with 1 year manufacture guarantee'),(23,5,'https://www.ebay.co.uk/itm/133941468449',217.99,'New iPhone 8 64GB Phone in assorted colour with 1 year manufacture guarantee'),(24,6,'https://www.ebay.co.uk/itm/133941452093',217.99,'New Apple iPhone 8 64GB Phone in Black with 1 year manufacture guarantee'),(25,5,'https://www.ebay.co.uk/p/239054210',209.94,'NEW Apple iPhone 8 64GB 256GB ⚫⚪🟡🔴 🔓GSM Unlocked ✅ AT&T✅ Verizon ✅ T-Mobile'),(26,13,'https://www.ebay.co.uk/itm/174539083666',324.90,'New Apple iPhone 8 128GB Unlocked Smartphone Small Box Fast & Free Deliver'),(27,1,'https://www.ebay.co.uk/itm/294270961007',865.00,'BNIB Apple iPhone 8 Special Ed. 64GB + 2GB Red Factory Unlocked 4G SIMFree'),(28,8,'https://www.ebay.co.uk/p/239093325',249.00,'Brand New Apple iPhone 8 A1905- 64GB - Unlocked Smartphone - Gold - Bargain!'),(29,14,'https://www.ebay.co.uk/p/245228137',650.00,'Apple iPhone 8 (PRODUCT)RED - 256GB - (Unlocked) A1905 (GSM)'),(30,11,'https://www.ebay.co.uk/itm/143649057844',329.95,'Apple iPhone 8 Space Grey 64GB Mobile Phone - Sim Free - 1 Year Apple Warranty'),(31,12,'https://www.ebay.co.uk/itm/143979887585',528.65,'Apple iPhone 8 64GB Silver UNLOCKED - NEW'),(32,15,'https://www.ebay.co.uk/itm/274567976882',299.99,'Apple iPhone 8 256 GB Space Grey SIM-Free Smartphone With Accessories'),(33,13,'https://www.ebay.co.uk/p/13043614982',186.88,'New Apple iPhone 8 256GB 128GB Smartphone 4G Unlocked SIM Free Original Gift'),(34,5,'https://www.ebay.co.uk/itm/373775527352',242.66,'Apple IPHONE 8 64GB - Smartphone - New Product Incl. Tax'),(35,16,'https://www.ebay.co.uk/itm/373793153145',271.10,'Apple IPHONE 8 256GB - Smartphone - New Product Incl. Vat'),(36,6,'https://www.ebay.co.uk/itm/223511635024',239.16,'Apple iPhone 8 64GB | 128GB 256GB (GSM UNLOCKED) BLACK SILVER GOLD RED ❖SEALED❖W'),(37,6,'https://www.ebay.co.uk/itm/323484658185',239.16,'Apple iPhone 8 - 64GB 128GB 256GB (UNLOCKED) VERIZON BLACK│SILVER│RED ❖SEALED❖'),(38,6,'https://www.ebay.co.uk/itm/165142446948',186.88,'New Apple iPhone 8/8 Plus 128GB 64/256GB Unlocked Black Gold Silver Red Original'),(39,8,'https://www.ebay.co.uk/itm/164763690724',186.88,'New Apple iPhone 8 256GB 64GB Gold Silver Gray 4G iOS 14 Smartphone Unlocked UK'),(40,5,'https://www.ebay.co.uk/p/7034037589',279.99,'Apple iPhone 8 GREY 64GB SIM FREE Mobile PHONE Unlocked UK SELLER FREE DELIVERY '),(41,6,'https://www.ebay.co.uk/p/2314763278',239.16,'Apple iPhone 8 - 64GB 128GB 256GB (UNLOCKED) VERIZON BLACK│SILVER│RED ❖SEALED❖'),(42,6,'https://www.ebay.co.uk/itm/363411609746',209.99,'Sealed Apple iPhone 8 - A1905 - 64GB - Black - Factory Unlocked - Sim Free');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-28 18:54:32
