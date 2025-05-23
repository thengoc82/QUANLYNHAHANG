--DROP DATABASE NhaHangTuQuyI;
USE [master]
GO
/****** Object:  Database  [NhaHangTuQuyI]   Script Date: 13/03/2025 9:53:51 PM ******/
CREATE DATABASE [NhaHangTuQuyI]
GO
ALTER DATABASE [NhaHangTuQuyI] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [NhaHangTuQuyI].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [NhaHangTuQuyI] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [NhaHangTuQuyI] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [NhaHangTuQuyI] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [NhaHangTuQuyI] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [NhaHangTuQuyI] SET ARITHABORT OFF 
GO
ALTER DATABASE [NhaHangTuQuyI] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [NhaHangTuQuyI] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [NhaHangTuQuyI] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [NhaHangTuQuyI] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [NhaHangTuQuyI] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [NhaHangTuQuyI] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [NhaHangTuQuyI] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [NhaHangTuQuyI] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [NhaHangTuQuyI] SET RECOVERY FULL 
GO
ALTER DATABASE [NhaHangTuQuyI] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [NhaHangTuQuyI] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [NhaHangTuQuyI] SET  MULTI_USER 
GO
ALTER DATABASE [NhaHangTuQuyI] SET QUERY_STORE = ON
GO
ALTER DATABASE [NhaHangTuQuyI] SET QUERY_STORE 
(
	OPERATION_MODE = READ_WRITE, 
	CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), 
	DATA_FLUSH_INTERVAL_SECONDS = 900, 
	INTERVAL_LENGTH_MINUTES = 60, 
	MAX_STORAGE_SIZE_MB = 1000, 
	QUERY_CAPTURE_MODE = AUTO, 
	SIZE_BASED_CLEANUP_MODE = AUTO, 
	MAX_PLANS_PER_QUERY = 200, 
	WAIT_STATS_CAPTURE_MODE = ON
)
GO
ALTER DATABASE [NhaHangTuQuyI] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [NhaHangTuQuyI] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [NhaHangTuQuyI] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [NhaHangTuQuyI] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [NhaHangTuQuyI] SET  ENABLE_BROKER 
GO
ALTER DATABASE [NhaHangTuQuyI] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [NhaHangTuQuyI] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [NhaHangTuQuyI] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [NhaHangTuQuyI] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [NhaHangTuQuyI] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [NhaHangTuQuyI] SET DB_CHAINING OFF 
GO
ALTER DATABASE [NhaHangTuQuyI] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [NhaHangTuQuyI] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'NhaHangTuQuyI', N'ON'
GO
USE [NhaHangTuQuyI]
go
/****** Object:  Table [dbo].[NhanVien]    Script Date: 15/03/2025 9:53:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE NhanVien (
    maNhanVien VARCHAR(9),
    tenNV NVARCHAR(200) NOT NULL,
    cCCD VARCHAR(12) UNIQUE NOT NULL,
    soDienThoai VARCHAR(10) NOT NULL,
    gioiTinh BIT NOT NULL,
    ngaySinh DATE NOT NULL,
    diaChi NVARCHAR(200) NOT NULL,
    taiKhoan VARCHAR(20) NOT NULL,
    email NVARCHAR(100) NOT NULL,
    PRIMARY KEY CLUSTERED 
    (
        maNhanVien ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY];

/****** Object:  Table [dbo].[Ban]   Script Date: 15/03/2025 9:53:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE Ban (
    soBan VARCHAR(3),
    trangThai BIT NOT NULL,
    viTri INT NOT NULL,
    loaiBan NVARCHAR(20) NOT NULL,
    PRIMARY KEY CLUSTERED 
    (
        soBan ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY];

/****** Object:  Table [dbo].[MonAn]    Script Date: 15/03/2025 9:53:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE MonAn (
    maMonAn VARCHAR(5) NOT NULL,
    tenMonAn NVARCHAR(50) NOT NULL,
    donGia MONEY CHECK (donGia >= 0),
    moTa NVARCHAR(200) NULL,
    trangThai BIT DEFAULT (1) NULL,
    loaiMonAn NVARCHAR(50) NOT NULL,
    urlHinhAnh NVARCHAR(MAX) NULL,
    maChuongTrinhKhuyenMai VARCHAR(10) NOT NULL,
    PRIMARY KEY CLUSTERED 
    (
        maMonAn ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY];

GO

/****** Object:  Table [dbo].[KhachHang]    Script Date: 15/03/2025 9:53:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE KhachHang (
    maKH VARCHAR(11),
    tenKH NVARCHAR(50) NOT NULL,
    soDienThoai VARCHAR(10) NOT NULL,
    loaiKH BIT,
    PRIMARY KEY CLUSTERED 
    (
        maKH ASC
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]

/****** Object:  Table [dbo].[HoaDon]    Script Date: 15/03/2025 9:53:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE HoaDon (
    maHoaDon VARCHAR(12) NOT NULL,
    HESOPHUTHU DECIMAL(5,2) DEFAULT 0.05 CHECK (HESOPHUTHU >= 0 AND HESOPHUTHU <= 1),
    tongTien DECIMAL(18,2) CHECK (tongTien >= 0),
    thanhTien DECIMAL(18,2) CHECK (thanhTien >= 0),
    maChuongTrinhKhuyenMai VARCHAR(10) NULL,
    soBan VARCHAR(3) NOT NULL,
    gioXuatHoaDon DATETIME NOT NULL DEFAULT GETDATE(),
    maKhachHang VARCHAR(11) NULL,
    maNhanVien VARCHAR(9) NOT NULL,
    trangThai BIT NOT NULL DEFAULT 1,
    PRIMARY KEY CLUSTERED 
    (
        maHoaDon ASC
    ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, 
            ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) 
    ON [PRIMARY]
) ON [PRIMARY];

GO


/****** Object:  Table [dbo].[KhuyenMai]    Script Date: 15/03/2025 9:53:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE KhuyenMai (
    maChuongTrinh VARCHAR(10) NOT NULL,
    tenChuongTrinh NVARCHAR(200) NOT NULL,
    chietKhau FLOAT CHECK (chietKhau >= 0 AND chietKhau <= 1),
    ngayBatDau DATE NULL,
    ngayKetThuc DATE NULL,
    trangThai BIT  DEFAULT (1) NULL,
PRIMARY KEY CLUSTERED 
(
    maChuongTrinh ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 15/03/2025 9:53:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE TaiKhoan (
    tenDangNhap VARCHAR(20) NOT NULL,
    matKhau NVARCHAR(255) NOT NULL,
    chucVu BIT NOT NULL CHECK (chucVu IN (0, 1)), -- 0: Nhân viên, 1: Người quản lý
PRIMARY KEY CLUSTERED 
(
    tenDangNhap ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[DonDatBanTruoc]   Script Date: 15/03/2025 9:53:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE DonDatBanTruoc (
    maDonDatBanTruoc VARCHAR(12) NOT NULL,
    thoiGianDatBan DATETIME CHECK (thoiGianDatBan >= GETDATE()),
    soLuongKhach INT CHECK (soLuongKhach > 0),
    maNhanVien VARCHAR(9) NOT NULL,
    maKhachHangDatTruoc VARCHAR(11) NOT NULL,
    soBan VARCHAR(3) NOT NULL,
    trangThai INT NOT NULL CHECK (trangThai IN (0, 1, 2)), -- 0: Chưa duyệt, 1: Đã duyệt, 2: Đã hủy
    ghiChu NVARCHAR(500) NULL,

    PRIMARY KEY CLUSTERED 
    (
        maDonDatBanTruoc ASC
    ) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, 
            ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) 
    ON [PRIMARY]
) ON [PRIMARY];
GO

GO

/****** Object:  Table [dbo].[ChiTietHoaDon]    Script Date: 15/03/2025 9:53:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE ChiTietHoaDon (
    maMonAn VARCHAR(5) NOT NULL,
    maHoaDon VARCHAR(12) NOT NULL,
    soMonAn INT CHECK (soMonAn >= 1),
    tongTienMon MONEY CHECK (tongTienMon >= 0),
    PRIMARY KEY CLUSTERED 
    (
        maMonAn, maHoaDon
    )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[BaoCaoCa]   Script Date: 15/03/2025 9:53:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE BaoCaoCa (
    maBaoCao VARCHAR(17) NOT NULL,
    thoiGianVaoCa DATETIME NOT NULL CHECK (thoiGianVaoCa >= '1900-01-01'), -- Đảm bảo giá trị hợp lệ
    thoiGianKetCa DATETIME NOT NULL,
    tongSoHoaDon INT NOT NULL,
    maNhanVien VARCHAR(9) NOT NULL,
    doanhThuCa FLOAT,
    soHoaDonGhiNo FLOAT,
    tongTienDauCa FLOAT,
    tongTienCuoiCa FLOAT,
PRIMARY KEY CLUSTERED 
(
    maBaoCao ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
go
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'001',0,1,N'Bàn đôi')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'002',0,1,N'Bàn đôi')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'003',0,1,N'Bàn đôi')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'004',1,1,N'Bàn nhóm 4 người')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'005',0,1,N'Bàn nhóm 4 người')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'006',0,1,N'Bàn nhóm 6 người')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'007',1,1,N'Bàn nhóm 6 người')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'101',0,1,N'Bàn đôi')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'102',0,1,N'Bàn đôi')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'103',0,1,N'Bàn đôi')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'104',1,1,N'Bàn nhóm 4 người')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'105',0,1,N'Bàn nhóm 4 người')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'106',0,1,N'Bàn nhóm 6 người')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'107',1,1,N'Bàn nhóm 6 người')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'201',0,2,N'Bàn đôi')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'202',0,2,N'Bàn đôi')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'203',0,2,N'Bàn đôi')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'204',1,2,N'Bàn nhóm 4 người')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'205',0,2,N'Bàn nhóm 4 người')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'206',0,2,N'Bàn nhóm 6 người')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'207',1,2,N'Bàn nhóm 6 người')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'301',0,3,N'Bàn đôi')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'302',0,3,N'Bàn đôi')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'303',0,3,N'Bàn đôi')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'304',1,3,N'Bàn nhóm 4 người')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'305',0,3,N'Bàn nhóm 4 người')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'306',0,3,N'Bàn nhóm 6 người')
INSERT [dbo].[Ban] ([soBan],[trangThai],[viTri],[loaiBan]) VALUES (N'307',1,3,N'Bàn nhóm 6 người')
GO
/*
Trong mã maBaoCao (VARCHAR(17)) của câu lệnh INSERT INTO,
phần XXX là số thứ tự của báo cáo ca trong ngày.
Ví dụ: BCYYYYMMDDxxx
BC20250315001 → Báo cáo ca thứ 001 trong ngày 15/03/2025.
BC20250315002 → Báo cáo ca thứ 002 trong ngày 15/03/2025.
BC20250316001 → Báo cáo ca thứ 001 trong ngày 15/03/2025.*/
INSERT [dbo].[BaoCaoCa] ([maBaoCao],[thoiGianVaoCa],[thoiGianKetCa],[tongSoHoaDon],[maNhanVien],[doanhThuCa],[soHoaDonGhiNo],[tongTienDauCa],[tongTienCuoiCa] ) 
VALUES ('BC20250315001', '2025-03-15 00:00:00', '2025-03-15 11:30:00', 20, 'NV2503001', 1000000.00, 2, 300000.00, 1300000.00)
INSERT [dbo].[BaoCaoCa] ([maBaoCao],[thoiGianVaoCa],[thoiGianKetCa],[tongSoHoaDon],[maNhanVien],[doanhThuCa],[soHoaDonGhiNo],[tongTienDauCa],[tongTienCuoiCa] ) 
VALUES ('BC20250315002', '2025-03-15 11:30:00', '2025-03-15 16:30:00', 25, 'NV2503002', 1250000.50, 3, 300000.00, 1550000.50)
INSERT [dbo].[BaoCaoCa] ([maBaoCao],[thoiGianVaoCa],[thoiGianKetCa],[tongSoHoaDon],[maNhanVien],[doanhThuCa],[soHoaDonGhiNo],[tongTienDauCa],[tongTienCuoiCa] ) 
VALUES ('BC20250315003', '2025-03-15 16:30:00', '2025-03-15 22:00:00', 30, 'NV2503003', 1500000.75, 5, 300000.00, 1800000.75);
INSERT [dbo].[BaoCaoCa] ([maBaoCao],[thoiGianVaoCa],[thoiGianKetCa],[tongSoHoaDon],[maNhanVien],[doanhThuCa],[soHoaDonGhiNo],[tongTienDauCa],[tongTienCuoiCa] ) 
VALUES ('BC20250315004', '2025-03-15 22:00:00', '2025-03-16 06:00:00', 18, 'NV2503004', 900000.00, 2, 300000.00, 1200000.00);
GO
INSERT [dbo].[NhanVien] ([maNhanVien],[tenNV],[cCCD],[soDienThoai],[gioiTinh],[ngaySinh],[diaChi],[taiKhoan],[email])
VALUES ('NV2503001', N'Nguyễn Văn A', '123456789012', '0987654321', 1, '1995-05-10', N'123 Lê Lợi, TP.HCM', N'TK000', 'vana@company.com')
INSERT [dbo].[NhanVien] ([maNhanVien],[tenNV],[cCCD],[soDienThoai],[gioiTinh],[ngaySinh],[diaChi],[taiKhoan],[email])
VALUES ('NV2503002', N'Trần Thị B', '234567890123', '0912345678', 0, '1998-08-25', N'456 Trần Hưng Đạo, Hà Nội',N'TK001', 'ttb@company.com')
INSERT [dbo].[NhanVien] ([maNhanVien],[tenNV],[cCCD],[soDienThoai],[gioiTinh],[ngaySinh],[diaChi],[taiKhoan],[email])
VALUES ('NV2503003', N'Lê Văn C', '345678901234', '0901234567', 1, '2000-01-15', N'789 Nguyễn Huệ, Đà Nẵng', N'TK002', 'lvc@company.com')
INSERT [dbo].[NhanVien] ([maNhanVien],[tenNV],[cCCD],[soDienThoai],[gioiTinh],[ngaySinh],[diaChi],[taiKhoan],[email])
VALUES ('NV2503004', N'Phạm Thị D', '456789012345', '0923456789', 0, '1992-11-30', N'321 Võ Văn Kiệt, Cần Thơ', N'TK003', 'ptd@company.com')
GO
INSERT [dbo].[TaiKhoan] ([tenDangNhap],[matKhau],[chucVu])
VALUES (N'TK000', N'pass0',0)
INSERT [dbo].[TaiKhoan] ([tenDangNhap],[matKhau],[chucVu])
VALUES (N'TK001', N'pass1',0)
INSERT [dbo].[TaiKhoan] ([tenDangNhap],[matKhau],[chucVu])
VALUES (N'TK002', N'pass2',0)
INSERT [dbo].[TaiKhoan] ([tenDangNhap],[matKhau],[chucVu])
VALUES (N'TK003', N'pass3',0)
INSERT [dbo].[TaiKhoan] ([tenDangNhap],[matKhau],[chucVu])
VALUES (N'TK004', N'pass4',1)/*admin*/
GO
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon],[maMonAn],[soMonAn],[tongTienMon]) VALUES ('HD2503240017', 'MA117', 3, 210000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon],[maMonAn],[soMonAn],[tongTienMon]) VALUES ('HD2503240018', 'MA118', 2, 140000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon],[maMonAn],[soMonAn],[tongTienMon]) VALUES ('HD2503240019', 'MA119', 1, 90000)
INSERT [dbo].[ChiTietHoaDon] ([maHoaDon],[maMonAn],[soMonAn],[tongTienMon]) VALUES ('HD2503240020', 'MA120', 4, 320000)
GO
INSERT [dbo].[MonAn] ([maMonAn], [tenMonAn], [donGia], [moTa], [trangThai], [loaiMonAn], [urlHinhAnh], [maChuongTrinhKhuyenMai])  
VALUES ('MA001', N'Phở Bò', 50000, N'Phở bò thơm ngon với nước dùng đậm đà', 1, N'Bữa sáng', NULL, 'KM25001');
INSERT [dbo].[MonAn] ([maMonAn], [tenMonAn], [donGia], [moTa], [trangThai], [loaiMonAn], [urlHinhAnh], [maChuongTrinhKhuyenMai])  
VALUES ('MA002', N'Bánh Mì Thịt', 25000, N'Bánh mì giòn rụm, nhân thịt đậm vị', 1, N'Ăn nhanh', NULL, 'KM25002');
INSERT [dbo].[MonAn] ([maMonAn], [tenMonAn], [donGia], [moTa], [trangThai], [loaiMonAn], [urlHinhAnh], [maChuongTrinhKhuyenMai])  
VALUES ('MA003', N'Cơm Gà', 60000, N'Cơm gà xối mỡ thơm ngon', 1, N'Món chính', NULL, 'KM25003');
INSERT [dbo].[MonAn] ([maMonAn], [tenMonAn], [donGia], [moTa], [trangThai], [loaiMonAn], [urlHinhAnh], [maChuongTrinhKhuyenMai])  
VALUES ('MA004', N'Lẩu Hải Sản', 300000, N'Lẩu hải sản tươi ngon với nước dùng đặc biệt', 1, N'Món chính', NULL, 'KM25004');


GO
INSERT [dbo].[KhuyenMai] ([maChuongTrinh], [tenChuongTrinh], [chietKhau], [ngayBatDau], [ngayKetThuc], [trangThai])  
VALUES ('KM25001', N'Khuyến mãi Tết', 0.105, '2025-01-20', '2025-02-05', 1);
INSERT [dbo].[KhuyenMai] ([maChuongTrinh], [tenChuongTrinh], [chietKhau], [ngayBatDau], [ngayKetThuc], [trangThai])  
VALUES ('KM25002', N'Giảm giá hè', 0.15, '2025-06-01', '2025-06-30', 1);
INSERT [dbo].[KhuyenMai] ([maChuongTrinh], [tenChuongTrinh], [chietKhau], [ngayBatDau], [ngayKetThuc], [trangThai])  
VALUES ('KM25003', N'Black Friday', 0.5, '2025-11-25', '2025-11-30', 1);
INSERT [dbo].[KhuyenMai] ([maChuongTrinh], [tenChuongTrinh], [chietKhau], [ngayBatDau], [ngayKetThuc], [trangThai])  
VALUES ('KM25004', N'Khuyến mãi Noel', NULL, '2025-12-20', NULL, 0)
GO
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503250001', '2025-03-25 18:00:00', 2, 'NV2503001', 'KH250324001', '101', 1, N'Khách yêu cầu không hút thuốc')
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503250002', '2025-03-25 19:00:00', 2, 'NV2503002', 'KH250324002', '102', 1, N'Bàn gần cửa sổ')
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503250003', '2025-03-25 20:00:00', 2, 'NV2503003', 'KH250324003', '103', 1, NULL)
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503250004', '2025-03-25 20:30:00', 4, 'NV2503004', 'KH250324004', '104', 1, N'Bàn nhóm 4 người - Đã có khách')
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503250005', '2025-03-25 21:00:00', 4, 'NV2503001', 'KH250324005', '105', 1, N'Bàn có không gian riêng tư')
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503250006', '2025-03-25 21:30:00', 6, 'NV2503002', 'KH250324006', '106', 1, NULL)
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503250007', '2025-03-25 22:00:00', 6, 'NV2503003', 'KH250324006', '107', 1, N'Bàn nhóm 6 người - Đã có khách')
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503260008', '2025-03-26 18:00:00', 2, 'NV2503004', 'KH250324006', '201', 1, NULL)
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503260009', '2025-03-26 19:00:00', 2, 'NV2503001', 'KH250324007', '202', 1, NULL)
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503260010', '2025-03-26 20:00:00', 2, 'NV2503002', 'KH250324008', '203', 1, NULL)
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503260011', '2025-03-26 20:30:00', 4, 'NV2503003', 'KH250324009', '204', 1, N'Bàn nhóm 4 người - Đã có khách')
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503260012', '2025-03-26 21:00:00', 4, 'NV2503004', 'KH250324010', '205', 1, NULL)
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503260013', '2025-03-26 21:30:00', 6, 'NV2503001', 'KH250324001', '206', 1, NULL)
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503260014', '2025-03-26 22:00:00', 6, 'NV2503002', 'KH250324002', '207', 1, N'Bàn nhóm 6 người - Đã có khách')
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503270015', '2025-03-27 18:00:00', 2, 'NV2503003', 'KH250324003', '301', 1, NULL)
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503270016', '2025-03-27 19:00:00', 2, 'NV2503004', 'KH250324004', '302', 1, NULL)
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503270017', '2025-03-27 20:00:00', 2, 'NV2503001', 'KH250324005', '303', 1, NULL)
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503270018', '2025-03-27 20:30:00', 4, 'NV2503002', 'KH250324006', '304', 1, N'Bàn nhóm 4 người - Đã có khách')
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503270019', '2025-03-27 21:00:00', 4, 'NV2503003', 'KH250324007', '305', 1, NULL)
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503270020', '2025-03-27 21:30:00', 6, 'NV2503004', 'KH250324008', '306', 1, NULL)
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503270021', '2025-03-27 22:00:00', 6, 'NV2503001', 'KH250324009', '307', 1, N'Bàn nhóm 6 người - Đã có khách')
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503280022', '2025-03-28 18:00:00', 2, 'NV2503002', 'KH250324010', '001', 1, NULL)
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503280023', '2025-03-28 19:00:00', 2, 'NV2503003', 'KH250324002', '002', 1, NULL)
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503280024', '2025-03-28 20:00:00', 2, 'NV2503004', 'KH250324001', '003', 1, NULL)
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503280025', '2025-03-28 20:30:00', 4, 'NV2503001', 'KH250324003', '004', 1, N'Bàn nhóm 4 người - Đã có khách')
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503280026', '2025-03-28 21:00:00', 4, 'NV2503002', 'KH250324002', '005', 1, NULL)
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503280027', '2025-03-28 21:30:00', 6, 'NV2503003', 'KH250324006', '006', 1, NULL)
INSERT [dbo].[DonDatBanTruoc] ([maDonDatBanTruoc], [thoiGianDatBan], [soLuongKhach], [maNhanVien], [maKhachHangDatTruoc], [soBan], [trangThai], [ghiChu])
VALUES ('DT2503280028', '2025-03-28 22:00:00', 6, 'NV2503004', 'KH250324010', '007', 1, N'Bàn nhóm 6 người - Đã có khách')
GO
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [soDienThoai], [loaiKH])
VALUES ('KH250324001', N'Nguyễn Văn A', '0912345678', 1)
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [soDienThoai], [loaiKH])
VALUES ('KH250324002', N'Trần Thị B', '0923456789', 0)
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [soDienThoai], [loaiKH])
VALUES ('KH250324003', N'Lê Văn C', '0934567890', NULL)
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [soDienThoai], [loaiKH])
VALUES ('KH250324004', N'Hoàng Minh D', '0945678901', 1)
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [soDienThoai], [loaiKH])
VALUES ('KH250324005', N'Phạm Thị E', '0956789012', 0)
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [soDienThoai], [loaiKH])
VALUES ('KH250324006', N'Đặng Quốc F', '0967890123', 1)
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [soDienThoai], [loaiKH])
VALUES ('KH250324007', N'Bùi Thanh G', '0978901234', NULL)
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [soDienThoai], [loaiKH])
VALUES ('KH250324008', N'Vũ Ngọc H', '0989012345', 0)
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [soDienThoai], [loaiKH])
VALUES ('KH250324009', N'Đỗ Hải I', '0990123456', 1)
INSERT [dbo].[KhachHang] ([maKH], [tenKH], [soDienThoai], [loaiKH])
VALUES ('KH250324010', N'Ngô Thị J', '0901234567', NULL)
GO
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240001', 0.05, 500000, 525000, 'KM25001', '001', '2024-03-24 12:30:00', 'KH250324010', 'NV2503001', 1)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240002', 0.05, 300000, 300000, NULL, '002', '2024-03-24 13:00:00', 'KH250324009', 'NV2503004', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240003', 0.05, 700000, 770000, 'KM25002', '003', '2024-03-24 14:15:00', 'KH250324008', 'NV2503003', 1)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240004', 0.05, 250000, 250000, NULL, '004', '2024-03-24 15:45:00', 'KH250324004', 'NV2503002', 1)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240005', 0.05, 600000, 648000, 'KM25003', '005', '2024-03-24 16:20:00', 'KH250324005', 'NV2503001', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240006', 0.05, 600000, 625000, 'KM25003', '006', '2024-03-24 17:00:00', 'KH250324006', 'NV2503004', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240007', 0.05, 500000, 525000, 'KM250001', '007', '2024-03-24 08:00:00', 'KH250324001', 'NV2503001', 1)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240008', 0.05, 750000, 825000, 'KM250002', '101', '2024-03-24 09:30:00', 'KH250324002', 'NV2503002', 1)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240009', 0.05, 620000, 651000, 'KM250003', '101', '2024-03-24 10:45:00', 'KH250324003', 'NV2503003', 1)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240010', 0.05, 820000, 902000, 'KM250004', '102', '2024-03-24 11:50:00', 'KH250324004', 'NV2503004', 1)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240011', 0.05, 540000, 567000, 'KM250001', '103', '2024-03-24 13:10:00', 'KH250324005', 'NV2503001', 1)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240012', 0.05, 680000, 748000, 'KM250002', '104', '2024-03-24 14:30:00', 'KH250324006', 'NV2503002', 1)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240013', 0.05, 460000, 483000, 'KM250003', '105', '2024-03-24 15:20:00', 'KH250324007', 'NV2503003', 1)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240014', 0.10, 900000, 990000, 'KM250004', '106', '2024-03-24 16:40:00', 'KH250324008', 'NV2503004', 1)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240015', 0.05, 720000, 756000, 'KM250001', '107', '2024-03-24 17:30:00', 'KH250324009', 'NV2503001', 1)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240016', 0.05, 850000, 935000, 'KM250002', '201', '2024-03-24 18:15:00', 'KH250324010', 'NV2503002', 1)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240017', 0.05, 670000, 703500, 'KM250003', '202', '2024-03-24 19:00:00', 'KH250324001', 'NV2503003', 1);
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240018', 0.05, 500000, 525000, 'KM25001', '201', '2024-03-24 18:00:00', 'KH250324001', 'NV2503001', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240019', 0.05, 550000, 577500, 'KM25002', '202', '2024-03-24 18:10:00', 'KH250324002', 'NV2503002', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240020', 0.05, 600000, 630000, 'KM25003', '203', '2024-03-24 18:20:00', 'KH250324003', 'NV2503003', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240021', 0.05, 650000, 682500, 'KM25004', '204', '2024-03-24 18:30:00', 'KH250324004', 'NV2503004', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240022', 0.05, 700000, 735000, 'KM25001', '205', '2024-03-24 18:40:00', 'KH250324005', 'NV2503001', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240023', 0.05, 750000, 787500, 'KM25002', '206', '2024-03-24 18:50:00', 'KH250324006', 'NV2503002', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240024', 0.05, 800000, 840000, 'KM25003', '207', '2024-03-24 19:00:00', 'KH250324007', 'NV2503003', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240025', 0.05, 850000, 892500, 'KM25004', '301', '2024-03-24 19:10:00', 'KH250324008', 'NV2503004', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240026', 0.05, 900000, 945000, 'KM25001', '302', '2024-03-24 19:20:00', 'KH250324009', 'NV2503001', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240027', 0.05, 950000, 997500, 'KM25002', '303', '2024-03-24 19:30:00', 'KH250324010', 'NV2503002', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240028', 0.05, 1000000, 1050000, 'KM25003', '304', '2024-03-24 19:40:00', 'KH250324011', 'NV2503003', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240029', 0.05, 1050000, 1102500, 'KM25004', '305', '2024-03-24 19:50:00', 'KH250324012', 'NV2503004', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240030', 0.05, 1100000, 1155000, 'KM25001', '306', '2024-03-24 20:00:00', 'KH250324013', 'NV2503001', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240031', 0.05, 1150000, 1207500, 'KM25002', '307', '2024-03-24 20:10:00', 'KH250324014', 'NV2503002', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240032', 0.05, 1200000, 1260000, 'KM25003', '101', '2024-03-24 20:20:00', 'KH250324015', 'NV2503003', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240033', 0.05, 1250000, 1312500, 'KM25004', '102', '2024-03-24 20:30:00', 'KH250324016', 'NV2503004', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240034', 0.05, 1300000, 1365000, 'KM25001', '103', '2024-03-24 20:40:00', 'KH250324017', 'NV2503001', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240035', 0.05, 1350000, 1417500, 'KM25002', '104', '2024-03-24 20:50:00', 'KH250324018', 'NV2503002', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240036', 0.05, 1400000, 1470000, 'KM25003', '105', '2024-03-24 21:00:00', 'KH250324019', 'NV2503003', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240037', 0.05, 1450000, 1522500, 'KM25004', '106', '2024-03-24 21:10:00', 'KH250324020', 'NV2503004', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240038', 0.05, 1500000, 1575000, 'KM25001', '107', '2024-03-24 21:20:00', 'KH250324021', 'NV2503001', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240039', 0.05, 1550000, 1627500, 'KM25002', '001', '2024-03-24 21:30:00', 'KH250324022', 'NV2503002', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240040', 0.05, 1600000, 1680000, 'KM25003', '002', '2024-03-24 21:40:00', 'KH250324023', 'NV2503003', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240041', 0.05, 1650000, 1732500, 'KM25004', '003', '2024-03-24 21:50:00', 'KH250324024', 'NV2503004', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240042', 0.05, 1700000, 1785000, 'KM25001', '004', '2024-03-24 22:00:00', 'KH250324025', 'NV2503001', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240043', 0.05, 1750000, 1837500, 'KM25002', '005', '2024-03-24 22:10:00', 'KH250324026', 'NV2503002', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240044', 0.05, 1800000, 1890000, 'KM25003', '006', '2024-03-24 22:20:00', 'KH250324027', 'NV2503003', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240045', 0.05, 1850000, 1942500, 'KM25004', '007', '2024-03-24 22:30:00', 'KH250324028', 'NV2503004', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240046', 0.05, 1900000, 1995000, 'KM25001', '101', '2024-03-24 22:40:00', 'KH250324029', 'NV2503001', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240047', 0.05, 1950000, 2047500, 'KM25002', '102', '2024-03-24 22:50:00', 'KH250324030', 'NV2503002', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240048', 0.05, 2000000, 2100000, 'KM25003', '103', '2024-03-24 23:00:00', 'KH250324031', 'NV2503003', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240049', 0.05, 2050000, 2152500, 'KM25004', '104', '2024-03-24 23:10:00', 'KH250324032', 'NV2503004', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240050', 0.05, 2100000, 2205000, 'KM25001', '105', '2024-03-24 23:20:00', 'KH250324033', 'NV2503001', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240051', 0.05, 2150000, 2257500, 'KM25002', '106', '2024-03-24 23:30:00', 'KH250324034', 'NV2503002', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240052', 0.05, 2200000, 2310000, 'KM25003', '107', '2024-03-24 23:40:00', 'KH250324035', 'NV2503003', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240053', 0.05, 2250000, 2362500, 'KM25004', '201', '2024-03-24 23:50:00', 'KH250324036', 'NV2503004', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240054', 0.05, 2300000, 2415000, 'KM25001', '202', '2024-03-25 00:00:00', 'KH250324037', 'NV2503001', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240055', 0.05, 2350000, 2467500, 'KM25002', '203', '2024-03-25 00:10:00', 'KH250324038', 'NV2503002', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240056', 0.05, 2400000, 2520000, 'KM25003', '204', '2024-03-25 00:20:00', 'KH250324039', 'NV2503003', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240057', 0.05, 2450000, 2572500, 'KM25004', '205', '2024-03-25 00:30:00', 'KH250324040', 'NV2503004', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240058', 0.05, 2500000, 2625000, 'KM25001', '206', '2024-03-25 00:40:00', 'KH250324041', 'NV2503001', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240059', 0.05, 2550000, 2677500, 'KM25002', '207', '2024-03-25 00:50:00', 'KH250324042', 'NV2503002', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240060', 0.05, 2600000, 2730000, 'KM25003', '301', '2024-03-25 01:00:00', 'KH250324043', 'NV2503003', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240061', 0.05, 2650000, 2782500, 'KM25004', '302', '2024-03-25 01:10:00', 'KH250324044', 'NV2503004', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240062', 0.05, 2700000, 2835000, 'KM25001', '303', '2024-03-25 01:20:00', 'KH250324045', 'NV2503001', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240063', 0.05, 2750000, 2887500, 'KM25002', '304', '2024-03-25 01:30:00', 'KH250324046', 'NV2503002', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240064', 0.05, 2800000, 2940000, 'KM25003', '305', '2024-03-25 01:40:00', 'KH250324047', 'NV2503003', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240065', 0.05, 2850000, 2992500, 'KM25004', '306', '2024-03-25 01:50:00', 'KH250324048', 'NV2503004', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240066', 0.05, 2900000, 3045000, 'KM25001', '307', '2024-03-25 02:00:00', 'KH250324049', 'NV2503001', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240067', 0.05, 2950000, 3097500, 'KM25002', '101', '2024-03-25 02:10:00', 'KH250324050', 'NV2503002', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240068', 0.05, 3000000, 3150000, 'KM25003', '102', '2024-03-25 02:20:00', 'KH250324051', 'NV2503003', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240069', 0.05, 3050000, 3202500, 'KM25004', '103', '2024-03-25 02:30:00', 'KH250324052', 'NV2503004', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240070', 0.05, 3100000, 3255000, 'KM25001', '104', '2024-03-25 02:40:00', 'KH250324053', 'NV2503001', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240071', 0.05, 3150000, 3307500, 'KM25002', '105', '2024-03-25 02:50:00', 'KH250324054', 'NV2503002', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240072', 0.05, 3200000, 3360000, 'KM25003', '106', '2024-03-25 03:00:00', 'KH250324055', 'NV2503003', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240073', 0.05, 3250000, 3412500, 'KM25004', '107', '2024-03-25 03:10:00', 'KH250324056', 'NV2503004', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240074', 0.05, 3300000, 3465000, 'KM25001', '001', '2024-03-25 03:20:00', 'KH250324057', 'NV2503001', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240075', 0.05, 3350000, 3517500, 'KM25002', '002', '2024-03-25 03:30:00', 'KH250324058', 'NV2503002', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240076', 0.05, 3400000, 3570000, 'KM25003', '003', '2024-03-25 03:40:00', 'KH250324059', 'NV2503003', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240077', 0.05, 3450000, 3622500, 'KM25004', '004', '2024-03-25 03:50:00', 'KH250324060', 'NV2503004', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240078', 0.05, 3500000, 3675000, 'KM25001', '005', '2024-03-25 04:00:00', 'KH250324061', 'NV2503001', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240079', 0.05, 3550000, 3727500, 'KM25002', '006', '2024-03-25 04:10:00', 'KH250324062', 'NV2503002', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240080', 0.05, 3600000, 3780000, 'KM25003', '007', '2024-03-25 04:20:00', 'KH250324063', 'NV2503003', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240081', 0.05, 3650000, 3832500, 'KM25004', '201', '2024-03-25 04:30:00', 'KH250324064', 'NV2503004', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240082', 0.05, 3700000, 3885000, 'KM25001', '202', '2024-03-25 04:40:00', 'KH250324065', 'NV2503001', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240083', 0.05, 3750000, 3937500, 'KM25002', '203', '2024-03-25 04:50:00', 'KH250324066', 'NV2503002', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240084', 0.05, 3800000, 3990000, 'KM25003', '204', '2024-03-25 05:00:00', 'KH250324067', 'NV2503003', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240085', 0.05, 3850000, 4042500, 'KM25004', '205', '2024-03-25 05:10:00', 'KH250324068', 'NV2503004', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240086', 0.05, 3900000, 4095000, 'KM25001', '206', '2024-03-25 05:20:00', 'KH250324069', 'NV2503001', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240087', 0.05, 3950000, 4147500, 'KM25002', '207', '2024-03-25 05:30:00', 'KH250324070', 'NV2503002', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240088', 0.05, 4000000, 4200000, 'KM25003', '301', '2024-03-25 05:40:00', 'KH250324071', 'NV2503003', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240089', 0.05, 4050000, 4252500, 'KM25004', '302', '2024-03-25 05:50:00', 'KH250324072', 'NV2503004', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240090', 0.05, 4100000, 4305000, 'KM25001', '303', '2024-03-25 06:00:00', 'KH250324073', 'NV2503001', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240091', 0.05, 4150000, 4357500, 'KM25002', '304', '2024-03-25 06:10:00', 'KH250324074', 'NV2503002', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240092', 0.05, 4200000, 4410000, 'KM25003', '305', '2024-03-25 06:20:00', 'KH250324075', 'NV2503003', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240093', 0.05, 4250000, 4462500, 'KM25004', '306', '2024-03-25 06:30:00', 'KH250324076', 'NV2503004', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240094', 0.05, 4300000, 4515000, 'KM25001', '307', '2024-03-25 06:40:00', 'KH250324077', 'NV2503001', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240095', 0.05, 4350000, 4567500, 'KM25002', '001', '2024-03-25 06:50:00', 'KH250324078', 'NV2503002', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240096', 0.05, 4400000, 4620000, 'KM25003', '002', '2024-03-25 07:00:00', 'KH250324079', 'NV2503003', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240097', 0.05, 4450000, 4672500, 'KM25004', '003', '2024-03-25 07:10:00', 'KH250324080', 'NV2503004', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240098', 0.05, 4500000, 4725000, 'KM25001', '004', '2024-03-25 07:20:00', 'KH250324081', 'NV2503001', 0)
INSERT [dbo].[HoaDon] ([maHoaDon], [HESOPHUTHU], [tongTien], [thanhTien], [maChuongTrinhKhuyenMai], [soBan], [gioXuatHoaDon], [maKhachHang], [maNhanVien], [trangThai])
VALUES ('HD2503240099', 0.05, 4550000, 4777500, 'KM25002', '005', '2024-03-25 07:30:00', 'KH250324082', 'NV2503002', 0)

go
-- Liên kết NhanVien với TaiKhoan
ALTER TABLE [dbo].[NhanVien]
WITH CHECK ADD CONSTRAINT [FK_NhanVien_TaiKhoan] 
FOREIGN KEY (taiKhoan) 
REFERENCES [dbo].[TaiKhoan] (tenDangNhap);
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [FK_NhanVien_TaiKhoan];
GO

-- Liên kết HoaDon với Ban, KhachHang, DonDatBanTruoc, NhanVien, KhuyenMai
ALTER TABLE [dbo].[HoaDon]
WITH CHECK ADD CONSTRAINT [FK_HoaDon_Ban] 
FOREIGN KEY (soBan) 
REFERENCES [dbo].[Ban] (soBan);
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_Ban];
GO

ALTER TABLE [dbo].[HoaDon]
WITH CHECK ADD CONSTRAINT [FK_HoaDon_KhachHang] 
FOREIGN KEY (maKhachHang) 
REFERENCES [dbo].[KhachHang] (maKH);
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_KhachHang];
GO

ALTER TABLE [dbo].[HoaDon]
WITH CHECK ADD CONSTRAINT [FK_HoaDon_NhanVien] 
FOREIGN KEY (maNhanVien) 
REFERENCES [dbo].[NhanVien] (maNhanVien);
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_NhanVien];
GO

ALTER TABLE [dbo].[HoaDon]
WITH CHECK ADD CONSTRAINT [FK_HoaDon_KhuyenMai] 
FOREIGN KEY (maChuongTrinhKhuyenMai) 
REFERENCES [dbo].[KhuyenMai] (maChuongTrinh);
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HoaDon_KhuyenMai];
GO

-- Liên kết ChiTietHoaDon với HoaDon và MonAn
-- Kiểm tra xem có dữ liệu không hợp lệ trước khi thêm FOREIGN KEY
-- Nếu có các giá trị không hợp lệ, chúng ta sẽ xóa hoặc thay đổi chúng.
-- Chỉ thêm FOREIGN KEY nếu các dữ liệu đã hợp lệ.

-- Xóa các bản ghi không hợp lệ trong ChiTietHoaDon:
-- Xóa các bản ghi trong ChiTietHoaDon nếu maHoaDon không tồn tại trong HoaDon
DELETE FROM ChiTietHoaDon
WHERE maHoaDon NOT IN (SELECT maHoaDon FROM HoaDon);
GO

-- Xóa các bản ghi trong ChiTietHoaDon nếu maMonAn không tồn tại trong MonAn
DELETE FROM ChiTietHoaDon
WHERE maMonAn NOT IN (SELECT maMonAn FROM MonAn);
GO

-- Thêm ràng buộc FOREIGN KEY sau khi đã xóa các bản ghi không hợp lệ
ALTER TABLE [dbo].[ChiTietHoaDon]
WITH CHECK ADD CONSTRAINT [FK_ChiTietHoaDon_HoaDon] 
FOREIGN KEY (maHoaDon) 
REFERENCES [dbo].[HoaDon] (maHoaDon);
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FK_ChiTietHoaDon_HoaDon];
GO

ALTER TABLE [dbo].[ChiTietHoaDon]
WITH CHECK ADD CONSTRAINT [FK_ChiTietHoaDon_MonAn] 
FOREIGN KEY (maMonAn) 
REFERENCES [dbo].[MonAn] (maMonAn);
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [FK_ChiTietHoaDon_MonAn];
GO

-- Liên kết DonDatBanTruoc với NhanVien, KhachHang, Ban
ALTER TABLE [dbo].[DonDatBanTruoc]
WITH CHECK ADD CONSTRAINT [FK_DonDatBanTruoc_NhanVien] 
FOREIGN KEY (maNhanVien) 
REFERENCES [dbo].[NhanVien] (maNhanVien);
GO
ALTER TABLE [dbo].[DonDatBanTruoc] CHECK CONSTRAINT [FK_DonDatBanTruoc_NhanVien];
GO

ALTER TABLE [dbo].[DonDatBanTruoc]
WITH CHECK ADD CONSTRAINT [FK_DonDatBanTruoc_KhachHang] 
FOREIGN KEY (maKhachHangDatTruoc) 
REFERENCES [dbo].[KhachHang] (maKH);
GO
ALTER TABLE [dbo].[DonDatBanTruoc] CHECK CONSTRAINT [FK_DonDatBanTruoc_KhachHang];
GO

ALTER TABLE [dbo].[DonDatBanTruoc]
WITH CHECK ADD CONSTRAINT [FK_DonDatBanTruoc_Ban] 
FOREIGN KEY (soBan) 
REFERENCES [dbo].[Ban] (soBan);
GO
ALTER TABLE [dbo].[DonDatBanTruoc] CHECK CONSTRAINT [FK_DonDatBanTruoc_Ban];
GO

-- Liên kết BaoCaoCa với NhanVien
ALTER TABLE [dbo].[BaoCaoCa]
WITH CHECK ADD CONSTRAINT [FK_BaoCaoCa_NhanVien] 
FOREIGN KEY (maNhanVien) 
REFERENCES [dbo].[NhanVien] (maNhanVien);
GO
ALTER TABLE [dbo].[BaoCaoCa] CHECK CONSTRAINT [FK_BaoCaoCa_NhanVien];
GO

-- Liên kết MonAn với KhuyenMai
ALTER TABLE [dbo].[MonAn]
WITH CHECK ADD CONSTRAINT [FK_MonAn_KhuyenMai] 
FOREIGN KEY (maChuongTrinhKhuyenMai) 
REFERENCES [dbo].[KhuyenMai] (maChuongTrinh);
GO
ALTER TABLE [dbo].[MonAn] CHECK CONSTRAINT [FK_MonAn_KhuyenMai];
GO

-- Liên kết HoaDon với Ban, KhachHang, DonDatBanTruoc, NhanVien, KhuyenMai
ALTER TABLE [dbo].[HoaDon] 
WITH CHECK ADD CONSTRAINT [FK_HD_Ban] 
FOREIGN KEY (soBan) 
REFERENCES [dbo].[Ban] (soBan);
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [FK_HD_Ban];
GO

GO
USE [master]
GO
ALTER DATABASE [NhaHangTuQuyI] SET  READ_WRITE 
GO


