create database HappyBakery

use HappyBakery

create table Users (
	UserID int identity(1,1) primary key,
	UserName nvarchar(100) not null,
	UserMail nvarchar(50) not null,
	UserPassword nvarchar(50) not null,
	UserPhone nvarchar(20),
	UserBDay date,
	UserAddress nvarchar(100),
	UserGender nvarchar(20),
	UserType nvarchar(10) not null,
	UserStatus int not null,
	UserImg nvarchar(100),
	UserToken nvarchar(100)
)

create table Category (
	CategoryID int identity(1,1) primary key,
	CategoryName nvarchar(50)
)

create table Country (
	CountryID int identity(1,1) primary key,
	CountryName nvarchar(50)
)

create table Recipe (
	RecipeID int identity(1,1) primary key,
	UserID int not null references Users(UserID),
	RecipeName nvarchar(50) not null,
	RecipeDescription nvarchar(300),
	RecipeAddedDay date,
	RecipeRating int not null,
	PrepTime int not null,
	CookTime int not null,
	Yields int not null,
	RecipeImg nvarchar(100),
	CategoryID int not null references Category(CategoryID),
	CountryID int not null references Country(CountryID)
)

create table SavedRecipe (
	UserID int not null references Users(UserID),
	RecipeID int not null references Recipe(RecipeID),
	primary key (UserID,RecipeID)
)

create table Blog (
	BlogID int identity(1,1) primary key,
	UserID int not null references Users(UserID),
	BlogImg nvarchar(50),
	BlogAddedDay date,
	BlogTitle nvarchar(50),
	BlogNote nvarchar(50),
	BlogDescription nvarchar(1000)
)

create table Comment (
	CommentID int identity(1,1) primary key,
	RecipeID int not null references Recipe(RecipeID),
	UserID int not null references Users(UserID),
	CmtDescription nvarchar(300),
	RatingPoint int not null
)

create table Step (
	StepID int identity(1,1) primary key,
	StepDescription nvarchar(300),
	RecipeID int not null references Recipe(RecipeID),
)

create table Ingredient (
	IngredientID int identity(1,1) primary key,
	IngredientName nvarchar(50) not null,
	RecipeID int not null references Recipe(RecipeID),
	Price int not null,
	IngredientImg nvarchar(100)
)

create table StoreAvailabe (
	StoreID int not null references Users(UserID),
	IngredientID int not null references Ingredient(IngredientID),
	primary key (StoreID,IngredientID),
	Quantity int not null 
)

create table Orders (
	OrderID int identity(1,1) primary key,
	OrderDate date,
	ShipDate date,
	OrderStatus int, 
	UserID int not null references Users(UserID)
)

create table OrderDetail(
	DetailID int identity(1,1) primary key,
	IngredientID int not null references Ingredient(IngredientID),
	OrderID int not null references Orders(OrderID),
	Quantity int not null 
)

insert into Users values ('Nguyen Ngoc Thinh', 'thinh@gmail.com', 'thinhse', '0987654321', '2021-02-22', 'Quang Nam', 'Male', 'ADMIN', 1, '', '')
insert into Users values ('Nguyen Quoc Manh', 'manh@gmail.com', 'manhse', '0987654321', '2021-02-21', 'Binh Dinh', 'Male', 'STORE', 1, '', '')
insert into Users values ('Nguyen Ba Nhat Tai', 'tai@gmail.com', 'taise', '0987654321', '2021-02-20', 'Ben Tre', 'Male', 'USER', 1, '', '')
insert into Users values ('Nguyen Hoang Huy', 'huy@gmail.com', 'huyse', '0987654321', '2021-02-23', 'Sai Gon', 'Male', 'USER', 1, '', '')
insert into Users values ('Tran Van Thien', 'thien@gmail.com', 'thiense', '0987654321', '2021-02-24', 'Quang Nam', 'Male', 'USER', 1, '', '')
-- 5 newest display store
insert into Users values ('Tiem Banh Hoa Binh', 'hoabinhbaker@gmail.com', 'hbbaker', '0987654321', '2021-02-21', 'Binh Dinh', 'Male', 'STORE', 1, '', '')
insert into Users values ('Tiem Cooking 4Ever', 'cookin4ever@gmail.com', 'cookin4ever', '0987654321', '2021-02-21', 'Binh Dinh', 'Female', 'STORE', 1, '', '')
insert into Users values ('Tiem BDay Celeb', 'bdayceleb@gmail.com', 'bdayceleb', '0987654321', '2021-02-21', 'Binh Dinh', 'Female', 'STORE', 1, '', '')
insert into Users values ('Tiem Banh Hong Hoa', 'honghoabakery@gmail.com', 'honghoabakery', '0987654321', '2021-02-21', 'Binh Dinh', 'Female', 'STORE', 1, '', '')
insert into Users values ('Tiem Banh Nhan Ai', 'nhanai@gmail.com', 'nhanai123', '0987654321', '2021-02-21', 'Binh Dinh', 'Male', 'STORE', 1, '', '')

insert into Category values ('Cake')
insert into Category values ('Biscuit')
insert into Category values ('Salty Cake')

insert into Country values ('Viet Nam')
insert into Country values ('France')
insert into Country values ('USA')

-- 5 newest display store
insert into Recipe values ('3', 'Crepe', 'So Delicious', '', '5', '30', '40', '3', '', '1', '1')
insert into Recipe values ('3', 'Waffle', 'Pretty Good', '', '4', '60', '50', '4', '', '1', '2')
insert into Recipe values ('3', 'Croissant', 'Okay to eat', '', '5', '30', '40', '3', '', '1', '1')
insert into Recipe values ('3', 'Cheese Cake', 'Yummy', '', '4', '60', '50', '4', '', '1', '2')
insert into Recipe values ('3', 'Sandwich', 'Juicy with jams', '', '5', '30', '40', '3', '', '1', '1')

insert into Step values ('Prepared the ingredients', '1')
insert into Step values ('Mix those up', '1')
insert into Step values ('Cook in the total time', '1')

insert into Comment values ('1', '4', 'Not bad at all, all my friends love it!', '5')
insert into Comment values ('2', '5', 'The ingredients is not healthy in my opinion', '1')
insert into Comment values ('3', '4', 'Not bad at all, all my friends love it!', '4')
insert into Comment values ('4', '5', 'The ingredients is not healthy in my opinion', '3')
insert into Comment values ('5', '4', 'Not bad at all, all my friends love it!', '2')
insert into Comment values ('1', '5', 'The ingredients is not healthy in my opinion', '0')

insert into SavedRecipe values ('4', '1')
insert into SavedRecipe values ('5', '1')

insert into Ingredient values ('Flour', '1', '5', '')
insert into Ingredient values ('Egg', '1', '2', '')
insert into Ingredient values ('Baking Soda', '1', '2', '')
insert into Ingredient values ('Strawberry', '1', '6', '')

insert into StoreAvailabe values ('2', '1', '200')
insert into StoreAvailabe values ('2', '2', '100')
insert into StoreAvailabe values ('2', '3', '50')

insert into Orders values ('', '', '1', '3')
insert into Orders values ('', '', '1', '4')
insert into Orders values ('', '', '1', '5')

insert into OrderDetail values ('1', '1', '10')
insert into OrderDetail values ('2', '1', '20')
insert into OrderDetail values ('3', '1', '30')

-- 5 newest display store
insert into Blog values ('3', '', '', 'Best Cooking Machine', 'It makes cooking easier than ever before', 'Its an flour mixer!')
insert into Blog values ('4', '', '', 'Making You In Love With Cooking', 'A great machine, help you to cook anything', 'Its an oven')
insert into Blog values ('3', '', '', 'Drying Machine', 'Dry Food everywhere!', 'Its an flour mixer!')
insert into Blog values ('4', '', '', 'Temperature Calculator', 'Perfect steak, why not?', 'Its an oven')
insert into Blog values ('3', '', '', 'Ultimate Cooker', 'Delicious meal everyday', 'Its an flour mixer!')












