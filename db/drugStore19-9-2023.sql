USE [master]
GO
/****** Object:  Database [DrugStore]    Script Date: 9/19/2023 11:13:22 PM ******/
CREATE DATABASE [DrugStore]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'DrugStore', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.DUCTRONG\MSSQL\DATA\DrugStore.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'DrugStore_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.DUCTRONG\MSSQL\DATA\DrugStore_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [DrugStore] SET COMPATIBILITY_LEVEL = 150
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
ALTER DATABASE [DrugStore] SET  ENABLE_BROKER 
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
ALTER DATABASE [DrugStore] SET QUERY_STORE = OFF
GO
USE [DrugStore]
GO
/****** Object:  Table [dbo].[Brand]    Script Date: 9/19/2023 11:13:22 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Brand](
	[brandId] [int] IDENTITY(1,1) NOT NULL,
	[brandName] [nvarchar](100) NOT NULL,
	[nationId] [int] NOT NULL,
	[description] [ntext] NULL,
	[avatar] [nvarchar](255) NULL,
 CONSTRAINT [PK_Brand] PRIMARY KEY CLUSTERED 
(
	[brandId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 9/19/2023 11:13:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[customerId] [int] IDENTITY(1,1) NOT NULL,
	[firstName] [nvarchar](255) NULL,
	[lastName] [nvarchar](255) NULL,
	[email] [nvarchar](255) NULL,
	[phoneNumber] [varchar](255) NULL,
	[password] [varchar](255) NULL,
 CONSTRAINT [PK_customer] PRIMARY KEY CLUSTERED 
(
	[customerId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Nation]    Script Date: 9/19/2023 11:13:23 PM ******/
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
/****** Object:  Table [dbo].[Order]    Script Date: 9/19/2023 11:13:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order](
	[orderId] [int] IDENTITY(1,1) NOT NULL,
	[recipientName] [nvarchar](255) NULL,
	[recipientPhoneNumber] [varchar](255) NULL,
	[recipientAddress] [nvarchar](255) NULL,
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
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 9/19/2023 11:13:23 PM ******/
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
/****** Object:  Table [dbo].[OrderStatus]    Script Date: 9/19/2023 11:13:23 PM ******/
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
/****** Object:  Table [dbo].[Payment]    Script Date: 9/19/2023 11:13:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Payment](
	[paymentId] [int] IDENTITY(1,1) NOT NULL,
	[paymentName] [nvarchar](100) NOT NULL,
	[description] [ntext] NULL,
 CONSTRAINT [PK_payment] PRIMARY KEY CLUSTERED 
(
	[paymentId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 9/19/2023 11:13:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[productId] [int] IDENTITY(1,1) NOT NULL,
	[productName] [nvarchar](255) NOT NULL,
	[description] [ntext] NULL,
	[uses] [nvarchar](255) NULL,
	[userManual] [nvarchar](255) NULL,
	[sideEffects] [nvarchar](255) NULL,
	[storage] [nvarchar](255) NULL,
	[note] [nvarchar](255) NULL,
	[categoryId] [int] NOT NULL,
	[brandId] [int] NOT NULL,
	[dosageFormId] [int] NOT NULL,
	[totalNumber] [int] NOT NULL,
	[soldNumber] [int] NOT NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[productId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductCategory]    Script Date: 9/19/2023 11:13:23 PM ******/
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
/****** Object:  Table [dbo].[ProductComment]    Script Date: 9/19/2023 11:13:23 PM ******/
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
	[subject] [ntext] NULL,
 CONSTRAINT [PK_ProductComment] PRIMARY KEY CLUSTERED 
(
	[commentId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductDosageForm]    Script Date: 9/19/2023 11:13:23 PM ******/
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
/****** Object:  Table [dbo].[ProductGroup]    Script Date: 9/19/2023 11:13:23 PM ******/
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
/****** Object:  Table [dbo].[ProductImage]    Script Date: 9/19/2023 11:13:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductImage](
	[imageId] [int] IDENTITY(1,1) NOT NULL,
	[imageName] [varbinary](max) NOT NULL,
	[productId] [int] NOT NULL,
 CONSTRAINT [PK_ProductImage] PRIMARY KEY CLUSTERED 
(
	[imageId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductIngredient]    Script Date: 9/19/2023 11:13:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductIngredient](
	[ingredientId] [int] IDENTITY(1,1) NOT NULL,
	[ingredientName] [nvarchar](255) NOT NULL,
	[description] [ntext] NULL,
 CONSTRAINT [PK_ProductIngredient] PRIMARY KEY CLUSTERED 
(
	[ingredientId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductIngredientDetail]    Script Date: 9/19/2023 11:13:23 PM ******/
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
/****** Object:  Table [dbo].[ProductRate]    Script Date: 9/19/2023 11:13:23 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductRate](
	[rateId] [int] IDENTITY(1,1) NOT NULL,
	[star] [int] NOT NULL,
	[time] [datetime] NOT NULL,
	[productId] [int] NOT NULL,
	[customerId] [int] NOT NULL,
 CONSTRAINT [PK_ProductRate] PRIMARY KEY CLUSTERED 
(
	[rateId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductSpecifyDetail]    Script Date: 9/19/2023 11:13:23 PM ******/
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
/****** Object:  Table [dbo].[ProductSpecifyFor]    Script Date: 9/19/2023 11:13:23 PM ******/
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
/****** Object:  Table [dbo].[ProductType]    Script Date: 9/19/2023 11:13:23 PM ******/
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
/****** Object:  Table [dbo].[ProductUnit]    Script Date: 9/19/2023 11:13:23 PM ******/
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
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductUnitDetail]    Script Date: 9/19/2023 11:13:23 PM ******/
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
/****** Object:  Table [dbo].[ProductUseDetail]    Script Date: 9/19/2023 11:13:23 PM ******/
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
/****** Object:  Table [dbo].[ProductUseFor]    Script Date: 9/19/2023 11:13:23 PM ******/
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
SET IDENTITY_INSERT [dbo].[Brand] ON 

INSERT [dbo].[Brand] ([brandId], [brandName], [nationId], [description], [avatar]) VALUES (5, N'Royal Care', 5, NULL, NULL)
INSERT [dbo].[Brand] ([brandId], [brandName], [nationId], [description], [avatar]) VALUES (6, N'Pharma World', 9, NULL, NULL)
SET IDENTITY_INSERT [dbo].[Brand] OFF
GO
SET IDENTITY_INSERT [dbo].[Customer] ON 

INSERT [dbo].[Customer] ([customerId], [firstName], [lastName], [email], [phoneNumber], [password]) VALUES (6, N'Ivat', N'Suki', N'uvat@gmail.com', N'0952534910', NULL)
SET IDENTITY_INSERT [dbo].[Customer] OFF
GO
SET IDENTITY_INSERT [dbo].[Nation] ON 

INSERT [dbo].[Nation] ([nationId], [nationName]) VALUES (5, N'China')
INSERT [dbo].[Nation] ([nationId], [nationName]) VALUES (9, N'United States')
INSERT [dbo].[Nation] ([nationId], [nationName]) VALUES (7, N'VietNam')
SET IDENTITY_INSERT [dbo].[Nation] OFF
GO
SET IDENTITY_INSERT [dbo].[OrderStatus] ON 

INSERT [dbo].[OrderStatus] ([statusId], [statusName]) VALUES (2, N'cancel')
INSERT [dbo].[OrderStatus] ([statusId], [statusName]) VALUES (3, N'wait')
SET IDENTITY_INSERT [dbo].[OrderStatus] OFF
GO
SET IDENTITY_INSERT [dbo].[Payment] ON 

INSERT [dbo].[Payment] ([paymentId], [paymentName], [description]) VALUES (2, N'payment by credit', NULL)
SET IDENTITY_INSERT [dbo].[Payment] OFF
GO
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([productId], [productName], [description], [uses], [userManual], [sideEffects], [storage], [note], [categoryId], [brandId], [dosageFormId], [totalNumber], [soldNumber]) VALUES (3, N'Viên uống Pharma Word CalcI K-2 bổ sung canxi, giảm nguy cơ loãng xương (60 viên)', N'Hỗ trợ tăng cường sức khỏe xương khớp Pharma World Calci K2 là thực phẩm bổ sung dinh dưỡng tốt cho xương. Ngoài canxi, sản phẩm còn có thêm vitamin K2 giúp cơ thể hấp thu canxi tốt hơn, tăng cường sức khỏe xương khớp từ bên trong.', N'Người lớn uống 2 viên mỗi ngày sau bữa ăn hoặc theo khuyến cáo của chuyên gia y tế.', N'', N'Chưa có thông tin về tác dụng phụ của sản phẩm.', N'Bảo quản nơi khô ráo, tại phòng được kiểm soát nhiệt độ. Không tiếp xúc với nhiệt độ hoặc độ ẩm cao. Tránh xa tầm tay trẻ em', N'', 1, 6, 3, 100, 0)
SET IDENTITY_INSERT [dbo].[Product] OFF
GO
SET IDENTITY_INSERT [dbo].[ProductCategory] ON 

INSERT [dbo].[ProductCategory] ([categoryId], [categoryName], [groupId]) VALUES (1, N'Bổ sung Canxi & Vitamin D', 4)
SET IDENTITY_INSERT [dbo].[ProductCategory] OFF
GO
SET IDENTITY_INSERT [dbo].[ProductDosageForm] ON 

INSERT [dbo].[ProductDosageForm] ([dosageFormId], [dosageFormName]) VALUES (3, N'Viên nén')
SET IDENTITY_INSERT [dbo].[ProductDosageForm] OFF
GO
SET IDENTITY_INSERT [dbo].[ProductGroup] ON 

INSERT [dbo].[ProductGroup] ([groupId], [groupName], [typeId]) VALUES (4, N'Vitamin & Khoáng Chất', 1)
SET IDENTITY_INSERT [dbo].[ProductGroup] OFF
GO
SET IDENTITY_INSERT [dbo].[ProductIngredient] ON 

INSERT [dbo].[ProductIngredient] ([ingredientId], [ingredientName], [description]) VALUES (1, N'Magnesium', N'Magiê rất quan trọng đối với nhiều quá trình sinh hóa và do đó nó khá phổ biến ở người. Phần lớn magiê được lưu trữ trong xương (> 50%), trong khi phần còn lại được lưu trữ trong cơ, mô mềm, tế bào hồng cầu và huyết thanh. Điều này rất quan trọng về mặt chức năng vì xương hoạt động như một kho dự trữ trao đổi magiê và giúp duy trì mức magiê khỏe mạnh.Magiê đóng một vai trò quan trọng trong việc điều hòa một số quá trình của cơ thể bao gồm huyết áp, chuyển hóa insulin, co cơ, trương lực vận mạch, kích thích tim, dẫn truyền thần kinh và dẫn truyền thần kinh cơ. Sự gián đoạn nồng độ magiê cân bằng nội môi (thường là hạ huyết áp) có thể tác động đến hệ thần kinh, cơ hoặc có thể dẫn đến các bất thường về tim.')
SET IDENTITY_INSERT [dbo].[ProductIngredient] OFF
GO
SET IDENTITY_INSERT [dbo].[ProductRate] ON 

INSERT [dbo].[ProductRate] ([rateId], [star], [time], [productId], [customerId]) VALUES (2, 3, CAST(N'2023-09-16T07:00:00.000' AS DateTime), 3, 6)
SET IDENTITY_INSERT [dbo].[ProductRate] OFF
GO
INSERT [dbo].[ProductSpecifyDetail] ([productId], [specifyId]) VALUES (3, 1)
INSERT [dbo].[ProductSpecifyDetail] ([productId], [specifyId]) VALUES (3, 2)
GO
SET IDENTITY_INSERT [dbo].[ProductSpecifyFor] ON 

INSERT [dbo].[ProductSpecifyFor] ([specifyId], [specifyName]) VALUES (1, N'Còi xương')
INSERT [dbo].[ProductSpecifyFor] ([specifyId], [specifyName]) VALUES (2, N'Loãng xương')
SET IDENTITY_INSERT [dbo].[ProductSpecifyFor] OFF
GO
SET IDENTITY_INSERT [dbo].[ProductType] ON 

INSERT [dbo].[ProductType] ([typeId], [typeName]) VALUES (2, N'Dược mỹ phẩm')
INSERT [dbo].[ProductType] ([typeId], [typeName]) VALUES (1, N'Thực phẩm chức năng')
SET IDENTITY_INSERT [dbo].[ProductType] OFF
GO
SET IDENTITY_INSERT [dbo].[ProductUnit] ON 

INSERT [dbo].[ProductUnit] ([unitId], [unitName]) VALUES (1, N'Hộp')
SET IDENTITY_INSERT [dbo].[ProductUnit] OFF
GO
SET IDENTITY_INSERT [dbo].[ProductUseFor] ON 

INSERT [dbo].[ProductUseFor] ([useForId], [useForName]) VALUES (4, N'Người cao tuổi')
INSERT [dbo].[ProductUseFor] ([useForId], [useForName]) VALUES (2, N'Người lớn')
INSERT [dbo].[ProductUseFor] ([useForId], [useForName]) VALUES (5, N'Phụ nữ cho con bú')
INSERT [dbo].[ProductUseFor] ([useForId], [useForName]) VALUES (6, N'Phụ nữ có thai')
INSERT [dbo].[ProductUseFor] ([useForId], [useForName]) VALUES (1, N'Trẻ em')
SET IDENTITY_INSERT [dbo].[ProductUseFor] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_BrandName]    Script Date: 9/19/2023 11:13:23 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UK_BrandName] ON [dbo].[Brand]
(
	[brandName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_email]    Script Date: 9/19/2023 11:13:23 PM ******/
ALTER TABLE [dbo].[Customer] ADD  CONSTRAINT [UK_email] UNIQUE NONCLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_phone]    Script Date: 9/19/2023 11:13:23 PM ******/
ALTER TABLE [dbo].[Customer] ADD  CONSTRAINT [UK_phone] UNIQUE NONCLUSTERED 
(
	[phoneNumber] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_NationName]    Script Date: 9/19/2023 11:13:23 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UK_NationName] ON [dbo].[Nation]
(
	[nationName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_OrderStatusName]    Script Date: 9/19/2023 11:13:23 PM ******/
ALTER TABLE [dbo].[OrderStatus] ADD  CONSTRAINT [UK_OrderStatusName] UNIQUE NONCLUSTERED 
(
	[statusName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_paymentName]    Script Date: 9/19/2023 11:13:23 PM ******/
ALTER TABLE [dbo].[Payment] ADD  CONSTRAINT [UK_paymentName] UNIQUE NONCLUSTERED 
(
	[paymentName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_productName]    Script Date: 9/19/2023 11:13:23 PM ******/
ALTER TABLE [dbo].[Product] ADD  CONSTRAINT [UK_productName] UNIQUE NONCLUSTERED 
(
	[productName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [IX_Product]    Script Date: 9/19/2023 11:13:23 PM ******/
CREATE NONCLUSTERED INDEX [IX_Product] ON [dbo].[Product]
(
	[productId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_ProductCategoryName]    Script Date: 9/19/2023 11:13:23 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UK_ProductCategoryName] ON [dbo].[ProductCategory]
(
	[categoryName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UKdpy5eh85pur6rqlnnq7n7mir5]    Script Date: 9/19/2023 11:13:23 PM ******/
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
/****** Object:  Index [UK_ProductDosageFormName]    Script Date: 9/19/2023 11:13:23 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UK_ProductDosageFormName] ON [dbo].[ProductDosageForm]
(
	[dosageFormName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_ProductGroupName]    Script Date: 9/19/2023 11:13:23 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UK_ProductGroupName] ON [dbo].[ProductGroup]
(
	[groupName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_ProductIngredientName]    Script Date: 9/19/2023 11:13:23 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UK_ProductIngredientName] ON [dbo].[ProductIngredient]
(
	[ingredientName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UK_ProductRate_Two_CustomerId_ProductId]    Script Date: 9/19/2023 11:13:23 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UK_ProductRate_Two_CustomerId_ProductId] ON [dbo].[ProductRate]
(
	[customerId] ASC,
	[productId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [UKdt6c1tn4av3vu1y1pp38e51tf]    Script Date: 9/19/2023 11:13:23 PM ******/
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
/****** Object:  Index [UK_ProductSpecifyForName]    Script Date: 9/19/2023 11:13:23 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UK_ProductSpecifyForName] ON [dbo].[ProductSpecifyFor]
(
	[specifyName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_ProductTypeName]    Script Date: 9/19/2023 11:13:23 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [UK_ProductTypeName] ON [dbo].[ProductType]
(
	[typeName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_ProductUnitName]    Script Date: 9/19/2023 11:13:23 PM ******/
ALTER TABLE [dbo].[ProductUnit] ADD  CONSTRAINT [UK_ProductUnitName] UNIQUE NONCLUSTERED 
(
	[unitName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UK_ProductUseForName]    Script Date: 9/19/2023 11:13:23 PM ******/
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
