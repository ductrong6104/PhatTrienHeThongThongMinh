USE [master]
GO
/****** Object:  Database [DrugStore]    Script Date: 4/09/2023 1:25:28 PM ******/
CREATE DATABASE [DrugStore]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'DrugStore', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\DrugStore.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'DrugStore_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\DrugStore_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [DrugStore] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [DrugStore].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [DrugStore] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [DrugStore] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [DrugStore] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [DrugStore] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [DrugStore] SET ARITHABORT OFF 
GO
ALTER DATABASE [DrugStore] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [DrugStore] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [DrugStore] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [DrugStore] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [DrugStore] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [DrugStore] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [DrugStore] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [DrugStore] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [DrugStore] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [DrugStore] SET  DISABLE_BROKER 
GO
ALTER DATABASE [DrugStore] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [DrugStore] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [DrugStore] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [DrugStore] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [DrugStore] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [DrugStore] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [DrugStore] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [DrugStore] SET RECOVERY FULL 
GO
ALTER DATABASE [DrugStore] SET  MULTI_USER 
GO
ALTER DATABASE [DrugStore] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [DrugStore] SET DB_CHAINING OFF 
GO
ALTER DATABASE [DrugStore] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [DrugStore] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [DrugStore] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [DrugStore] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'DrugStore', N'ON'
GO
ALTER DATABASE [DrugStore] SET QUERY_STORE = ON
GO
ALTER DATABASE [DrugStore] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [DrugStore]
GO
/****** Object:  Table [dbo].[Brand]    Script Date: 4/09/2023 1:25:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Brand](
	[brandId] [int] NOT NULL,
	[brandName] [nvarchar](100) NOT NULL,
	[nationId] [int] NOT NULL,
	[description] [varchar](255) NULL,
	[avatar] [nvarchar](255) NULL,
 CONSTRAINT [PK_Brand] PRIMARY KEY CLUSTERED 
(
	[brandId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 4/09/2023 1:25:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[customerId] [int] IDENTITY(1,1) NOT NULL,
	[firstName] [varchar](255) NULL,
	[lastName] [varchar](255) NULL,
	[email] [nvarchar](255) NULL,
	[phoneNumber] [varchar](255) NULL,
	[password] [varchar](255) NULL,
 CONSTRAINT [PK_customer] PRIMARY KEY CLUSTERED 
(
	[customerId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Nation]    Script Date: 4/09/2023 1:25:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Nation](
	[nationId] [int] IDENTITY(1,1) NOT NULL,
	[nationName] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_Nation] PRIMARY KEY CLUSTERED 
(
	[nationId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order]    Script Date: 4/09/2023 1:25:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[orderId] [int] IDENTITY(1,1) NOT NULL,
	[recipientName] [varchar](255) NULL,
	[recipientPhoneNumber] [varchar](255) NULL,
	[recipientAddress] [varchar](255) NULL,
	[buyOnline] [bit] NOT NULL,
	[statusId] [int] NOT NULL,
	[paymentId] [int] NOT NULL,
	[customerId] [int] NULL,
 CONSTRAINT [PK_Order] PRIMARY KEY CLUSTERED 
(
	[orderId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 4/09/2023 1:25:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[productId] [int] NOT NULL,
	[orderId] [int] NOT NULL,
 CONSTRAINT [PK_OrderDetail] PRIMARY KEY CLUSTERED 
(
	[productId] ASC,
	[orderId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderStatus]    Script Date: 4/09/2023 1:25:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderStatus](
	[statusId] [int] IDENTITY(1,1) NOT NULL,
	[statusName] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_OrderStatus] PRIMARY KEY CLUSTERED 
(
	[statusId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Payment]    Script Date: 4/09/2023 1:25:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Payment](
	[paymentId] [int] IDENTITY(1,1) NOT NULL,
	[paymentName] [nvarchar](100) NOT NULL,
	[description] [varchar](255) NULL,
 CONSTRAINT [PK_payment] PRIMARY KEY CLUSTERED 
(
	[paymentId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UK_paymentName] UNIQUE NONCLUSTERED 
(
	[paymentName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 4/09/2023 1:25:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[productId] [int] IDENTITY(1,1) NOT NULL,
	[productName] [varchar](255) NULL,
	[description] [varchar](255) NULL,
	[uses] [varchar](255) NULL,
	[userManual] [varchar](255) NULL,
	[sideEffects] [varchar](255) NULL,
	[storage] [varchar](255) NULL,
	[note] [varchar](255) NULL,
	[categoryId] [int] NOT NULL,
	[brandId] [int] NOT NULL,
	[dosageFormId] [int] NOT NULL,
	[totalNumber] [int] NOT NULL,
	[soldNumber] [int] NOT NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[productId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductCategory]    Script Date: 4/09/2023 1:25:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductCategory](
	[categoryId] [int] IDENTITY(1,1) NOT NULL,
	[categoryName] [nvarchar](100) NOT NULL,
	[groupId] [int] NOT NULL,
 CONSTRAINT [PK_ProductCategory] PRIMARY KEY CLUSTERED 
(
	[categoryId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductComment]    Script Date: 4/09/2023 1:25:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductComment](
	[commentId] [int] IDENTITY(1,1) NOT NULL,
	[customerId] [int] NOT NULL,
	[productId] [int] NULL,
	[replyForId] [int] NULL,
	[time] [datetime] NOT NULL,
	[content] [int] NULL,
 CONSTRAINT [PK_ProductComment] PRIMARY KEY CLUSTERED 
(
	[commentId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductDosageForm]    Script Date: 4/09/2023 1:25:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductDosageForm](
	[dosageFormId] [int] IDENTITY(1,1) NOT NULL,
	[dosageFormName] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_ProductDosageForm] PRIMARY KEY CLUSTERED 
(
	[dosageFormId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductGroup]    Script Date: 4/09/2023 1:25:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductGroup](
	[groupId] [int] IDENTITY(1,1) NOT NULL,
	[groupName] [nvarchar](100) NOT NULL,
	[typeId] [int] NOT NULL,
 CONSTRAINT [PK_ProductGroup] PRIMARY KEY CLUSTERED 
(
	[groupId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductImage]    Script Date: 4/09/2023 1:25:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductImage](
	[imageId] [int] IDENTITY(1,1) NOT NULL,
	[imageName] [nvarchar](255) NOT NULL,
	[productId] [int] NOT NULL,
 CONSTRAINT [PK_ProductImage] PRIMARY KEY CLUSTERED 
(
	[imageId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductIngredient]    Script Date: 4/09/2023 1:25:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductIngredient](
	[ingredientId] [int] IDENTITY(1,1) NOT NULL,
	[ingredientName] [nvarchar](255) NOT NULL,
	[description] [varchar](255) NULL,
 CONSTRAINT [PK_ProductIngredient] PRIMARY KEY CLUSTERED 
(
	[ingredientId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductIngredientDetail]    Script Date: 4/09/2023 1:25:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductIngredientDetail](
	[productId] [int] NOT NULL,
	[ingredientId] [int] NOT NULL,
 CONSTRAINT [PK_ProductIngredientDetail] PRIMARY KEY CLUSTERED 
(
	[productId] ASC,
	[ingredientId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductRate]    Script Date: 4/09/2023 1:25:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductRate](
	[rateId] [int] IDENTITY(1,1) NOT NULL,
	[productId] [int] NOT NULL,
	[customerId] [int] NOT NULL,
	[star] [int] NOT NULL,
	[time] [datetime] NOT NULL,
 CONSTRAINT [PK_ProductRate] PRIMARY KEY CLUSTERED 
(
	[rateId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductSpecifyDetail]    Script Date: 4/09/2023 1:25:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductSpecifyDetail](
	[productId] [int] NOT NULL,
	[specifyId] [int] NOT NULL,
 CONSTRAINT [PK_ProductSpecifyDetail] PRIMARY KEY CLUSTERED 
(
	[productId] ASC,
	[specifyId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductSpecifyFor]    Script Date: 4/09/2023 1:25:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductSpecifyFor](
	[specifyId] [int] IDENTITY(1,1) NOT NULL,
	[specifyName] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_ProductSpecifyFor] PRIMARY KEY CLUSTERED 
(
	[specifyId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductType]    Script Date: 4/09/2023 1:25:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductType](
	[typeId] [int] IDENTITY(1,1) NOT NULL,
	[typeName] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_ProductType] PRIMARY KEY CLUSTERED 
(
	[typeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductUnit]    Script Date: 4/09/2023 1:25:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductUnit](
	[unitId] [int] IDENTITY(1,1) NOT NULL,
	[unitName] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_ProductUnit] PRIMARY KEY CLUSTERED 
(
	[unitId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [UK_ProductUnitName] UNIQUE NONCLUSTERED 
(
	[unitName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductUnitDetail]    Script Date: 4/09/2023 1:25:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductUnitDetail](
	[productUnitId] [int] NOT NULL,
	[productId] [int] NOT NULL,
 CONSTRAINT [PK_ProductUnitDetail] PRIMARY KEY CLUSTERED 
(
	[productUnitId] ASC,
	[productId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductUseDetail]    Script Date: 4/09/2023 1:25:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductUseDetail](
	[useForId] [int] NOT NULL,
	[productId] [int] NOT NULL,
 CONSTRAINT [PK_ProductUseDetail] PRIMARY KEY CLUSTERED 
(
	[useForId] ASC,
	[productId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductUseFor]    Script Date: 4/09/2023 1:25:28 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductUseFor](
	[useForId] [int] IDENTITY(1,1) NOT NULL,
	[useForName] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_ProductUseFor] PRIMARY KEY CLUSTERED 
(
	[useForId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_BrandName]    Script Date: 4/09/2023 1:25:28 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UK_BrandName] ON [dbo].[Brand]
(
	[brandName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_NationName]    Script Date: 4/09/2023 1:25:28 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UK_NationName] ON [dbo].[Nation]
(
	[nationName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_OrderStatusName]    Script Date: 4/09/2023 1:25:28 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UK_OrderStatusName] ON [dbo].[OrderStatus]
(
	[statusName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [IX_Product]    Script Date: 4/09/2023 1:25:28 PM ******/
CREATE NONCLUSTERED INDEX [IX_Product] ON [dbo].[Product]
(
	[productId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_ProductCategoryName]    Script Date: 4/09/2023 1:25:28 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UK_ProductCategoryName] ON [dbo].[ProductCategory]
(
	[categoryName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UKdpy5eh85pur6rqlnnq7n7mir5]    Script Date: 4/09/2023 1:25:28 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UKdpy5eh85pur6rqlnnq7n7mir5] ON [dbo].[ProductComment]
(
	[productId] ASC,
	[customerId] ASC
)
WHERE ([productId] IS NOT NULL AND [customerId] IS NOT NULL)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_ProductDosageFormName]    Script Date: 4/09/2023 1:25:28 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UK_ProductDosageFormName] ON [dbo].[ProductDosageForm]
(
	[dosageFormName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_ProductGroupName]    Script Date: 4/09/2023 1:25:28 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UK_ProductGroupName] ON [dbo].[ProductGroup]
(
	[groupName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_ProductImageName]    Script Date: 4/09/2023 1:25:28 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UK_ProductImageName] ON [dbo].[ProductImage]
(
	[imageName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_ProductIngredientName]    Script Date: 4/09/2023 1:25:28 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UK_ProductIngredientName] ON [dbo].[ProductIngredient]
(
	[ingredientName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UK_ProductRate_Two_CustomerId_ProductId]    Script Date: 4/09/2023 1:25:28 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UK_ProductRate_Two_CustomerId_ProductId] ON [dbo].[ProductRate]
(
	[customerId] ASC,
	[productId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UKdt6c1tn4av3vu1y1pp38e51tf]    Script Date: 4/09/2023 1:25:28 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UKdt6c1tn4av3vu1y1pp38e51tf] ON [dbo].[ProductRate]
(
	[productId] ASC,
	[customerId] ASC
)
WHERE ([productId] IS NOT NULL AND [customerId] IS NOT NULL)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_ProductSpecifyForName]    Script Date: 4/09/2023 1:25:28 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UK_ProductSpecifyForName] ON [dbo].[ProductSpecifyFor]
(
	[specifyName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_ProductTypeName]    Script Date: 4/09/2023 1:25:28 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UK_ProductTypeName] ON [dbo].[ProductType]
(
	[typeName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_ProductUseForName]    Script Date: 4/09/2023 1:25:28 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UK_ProductUseForName] ON [dbo].[ProductUseFor]
(
	[useForName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Order] ADD  CONSTRAINT [DF_Order_buyOnline]  DEFAULT ((0)) FOR [buyOnline]
GO
ALTER TABLE [dbo].[Product] ADD  CONSTRAINT [DF_Product_totalNumber]  DEFAULT ((0)) FOR [totalNumber]
GO
ALTER TABLE [dbo].[Product] ADD  CONSTRAINT [DF_Product_soldNumber]  DEFAULT ((0)) FOR [soldNumber]
GO
ALTER TABLE [dbo].[ProductComment] ADD  CONSTRAINT [DF_ProductComment_time]  DEFAULT (getdate()) FOR [time]
GO
ALTER TABLE [dbo].[ProductRate] ADD  CONSTRAINT [DF_ProductRate_time]  DEFAULT (getdate()) FOR [time]
GO
ALTER TABLE [dbo].[Brand]  WITH CHECK ADD  CONSTRAINT [FK_Brand_Nation] FOREIGN KEY([nationId])
REFERENCES [dbo].[Nation] ([nationId])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Brand] CHECK CONSTRAINT [FK_Brand_Nation]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_customer] FOREIGN KEY([customerId])
REFERENCES [dbo].[Customer] ([customerId])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_customer]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_OrderStatus] FOREIGN KEY([statusId])
REFERENCES [dbo].[OrderStatus] ([statusId])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_OrderStatus]
GO
ALTER TABLE [dbo].[Order]  WITH CHECK ADD  CONSTRAINT [FK_Order_payment] FOREIGN KEY([paymentId])
REFERENCES [dbo].[Payment] ([paymentId])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Order] CHECK CONSTRAINT [FK_Order_payment]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Order] FOREIGN KEY([orderId])
REFERENCES [dbo].[Order] ([orderId])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_Order]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetail_Product] FOREIGN KEY([productId])
REFERENCES [dbo].[Product] ([productId])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK_OrderDetail_Product]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Brand] FOREIGN KEY([brandId])
REFERENCES [dbo].[Brand] ([brandId])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Brand]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_ProductCategory] FOREIGN KEY([categoryId])
REFERENCES [dbo].[ProductCategory] ([categoryId])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_ProductCategory]
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_ProductDosageForm] FOREIGN KEY([dosageFormId])
REFERENCES [dbo].[ProductDosageForm] ([dosageFormId])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_ProductDosageForm]
GO
ALTER TABLE [dbo].[ProductCategory]  WITH CHECK ADD  CONSTRAINT [FK_ProductCategory_ProductGroup] FOREIGN KEY([groupId])
REFERENCES [dbo].[ProductGroup] ([groupId])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[ProductCategory] CHECK CONSTRAINT [FK_ProductCategory_ProductGroup]
GO
ALTER TABLE [dbo].[ProductComment]  WITH CHECK ADD  CONSTRAINT [FK_ProductComment_customer] FOREIGN KEY([customerId])
REFERENCES [dbo].[Customer] ([customerId])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[ProductComment] CHECK CONSTRAINT [FK_ProductComment_customer]
GO
ALTER TABLE [dbo].[ProductComment]  WITH CHECK ADD  CONSTRAINT [FK_ProductComment_Product] FOREIGN KEY([productId])
REFERENCES [dbo].[Product] ([productId])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[ProductComment] CHECK CONSTRAINT [FK_ProductComment_Product]
GO
ALTER TABLE [dbo].[ProductComment]  WITH CHECK ADD  CONSTRAINT [FK_ProductComment_ProductComment] FOREIGN KEY([replyForId])
REFERENCES [dbo].[ProductComment] ([commentId])
GO
ALTER TABLE [dbo].[ProductComment] CHECK CONSTRAINT [FK_ProductComment_ProductComment]
GO
ALTER TABLE [dbo].[ProductGroup]  WITH CHECK ADD  CONSTRAINT [FK_ProductGroup_ProductType] FOREIGN KEY([typeId])
REFERENCES [dbo].[ProductType] ([typeId])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[ProductGroup] CHECK CONSTRAINT [FK_ProductGroup_ProductType]
GO
ALTER TABLE [dbo].[ProductImage]  WITH CHECK ADD  CONSTRAINT [FK_ProductImage_Product] FOREIGN KEY([productId])
REFERENCES [dbo].[Product] ([productId])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[ProductImage] CHECK CONSTRAINT [FK_ProductImage_Product]
GO
ALTER TABLE [dbo].[ProductIngredientDetail]  WITH CHECK ADD  CONSTRAINT [FK_ProductIngredientDetail_Product] FOREIGN KEY([productId])
REFERENCES [dbo].[Product] ([productId])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[ProductIngredientDetail] CHECK CONSTRAINT [FK_ProductIngredientDetail_Product]
GO
ALTER TABLE [dbo].[ProductIngredientDetail]  WITH CHECK ADD  CONSTRAINT [FK_ProductIngredientDetail_ProductIngredient] FOREIGN KEY([ingredientId])
REFERENCES [dbo].[ProductIngredient] ([ingredientId])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[ProductIngredientDetail] CHECK CONSTRAINT [FK_ProductIngredientDetail_ProductIngredient]
GO
ALTER TABLE [dbo].[ProductRate]  WITH CHECK ADD  CONSTRAINT [FK_ProductRate_customer] FOREIGN KEY([customerId])
REFERENCES [dbo].[Customer] ([customerId])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[ProductRate] CHECK CONSTRAINT [FK_ProductRate_customer]
GO
ALTER TABLE [dbo].[ProductRate]  WITH CHECK ADD  CONSTRAINT [FK_ProductRate_Product] FOREIGN KEY([productId])
REFERENCES [dbo].[Product] ([productId])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[ProductRate] CHECK CONSTRAINT [FK_ProductRate_Product]
GO
ALTER TABLE [dbo].[ProductSpecifyDetail]  WITH CHECK ADD  CONSTRAINT [FK_ProductSpecifyDetail_Product] FOREIGN KEY([productId])
REFERENCES [dbo].[Product] ([productId])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[ProductSpecifyDetail] CHECK CONSTRAINT [FK_ProductSpecifyDetail_Product]
GO
ALTER TABLE [dbo].[ProductSpecifyDetail]  WITH CHECK ADD  CONSTRAINT [FK_ProductSpecifyDetail_ProductSpecifyFor] FOREIGN KEY([specifyId])
REFERENCES [dbo].[ProductSpecifyFor] ([specifyId])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[ProductSpecifyDetail] CHECK CONSTRAINT [FK_ProductSpecifyDetail_ProductSpecifyFor]
GO
ALTER TABLE [dbo].[ProductUnitDetail]  WITH CHECK ADD  CONSTRAINT [FK_ProductUnitDetail_Product] FOREIGN KEY([productId])
REFERENCES [dbo].[Product] ([productId])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[ProductUnitDetail] CHECK CONSTRAINT [FK_ProductUnitDetail_Product]
GO
ALTER TABLE [dbo].[ProductUnitDetail]  WITH CHECK ADD  CONSTRAINT [FK_ProductUnitDetail_ProductUnit] FOREIGN KEY([productUnitId])
REFERENCES [dbo].[ProductUnit] ([unitId])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[ProductUnitDetail] CHECK CONSTRAINT [FK_ProductUnitDetail_ProductUnit]
GO
ALTER TABLE [dbo].[ProductUseDetail]  WITH CHECK ADD  CONSTRAINT [FK_ProductUseDetail_Product] FOREIGN KEY([productId])
REFERENCES [dbo].[Product] ([productId])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[ProductUseDetail] CHECK CONSTRAINT [FK_ProductUseDetail_Product]
GO
ALTER TABLE [dbo].[ProductUseDetail]  WITH CHECK ADD  CONSTRAINT [FK_ProductUseDetail_ProductUseFor] FOREIGN KEY([useForId])
REFERENCES [dbo].[ProductUseFor] ([useForId])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[ProductUseDetail] CHECK CONSTRAINT [FK_ProductUseDetail_ProductUseFor]
GO
ALTER TABLE [dbo].[ProductRate]  WITH CHECK ADD  CONSTRAINT [CK_ProductRateStar] CHECK  (([star]>(0) AND [star]<=(5)))
GO
ALTER TABLE [dbo].[ProductRate] CHECK CONSTRAINT [CK_ProductRateStar]
GO
USE [master]
GO
ALTER DATABASE [DrugStore] SET  READ_WRITE 
GO
