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
	RecipeRatingPoint int,
	PrepTime int not null,
	CookTime int not null,
	Yields int not null,
	RecipeImg nvarchar(100),
	CategoryID int not null references Category(CategoryID),
	CountryID int not null references Country(CountryID)
)

create table Badge (
	BadgeID int identity(1,1) primary key,
	BadgeName nvarchar(100) not null
)

create table BadgeReward (
	BadgeID int not null references Badge(BadgeID),
	UserID int not null references Users(UserID),
	primary key (BadgeID,UserID)
)

create table SavedRecipe (
	UserID int not null references Users(UserID),
	RecipeID int not null references Recipe(RecipeID),
	primary key (UserID,RecipeID)
)

create table BlogCategory (
	BlogCategoryID int identity(1,1) primary key,
	BlogCategoryName nvarchar(100)
)

create table Blog (
	BlogID int identity(1,1) primary key,
	UserID int not null references Users(UserID),
	BlogImg nvarchar(50),
	BlogAddedDay date,
	BlogTitle nvarchar(50),
	BlogNote nvarchar(50),
	BlogRatingPoint int,
	BlogDescription nvarchar(1000),
	BlogCategoryID int references BlogCategory(BlogCategoryID)
)

create table CommentRecipe (
	CommentID int identity(1,1) primary key,
	RatedRecipeID int not null references Recipe(RecipeID),
	UserID int not null references Users(UserID),
	CmtDescription nvarchar(300),
	RatingPoint int not null,
	CmtAddedDay date
)

create table CommentBlog (
	CommentID int identity(1,1) primary key,
	RatedBlogID int not null references Blog(BlogID),
	UserID int not null references Users(UserID),
	CmtDescription nvarchar(300),
	RatingPoint int not null,
	CmtAddedDay date
)

create table ReplyBlog (
	ReplyID int identity(1,1) primary key,
	CommentID int not null references CommentBlog(CommentID),
	Reply nvarchar(50)
)

create table ReplyRecipe (
	ReplyID int identity(1,1) primary key,
	CommentID int not null references CommentRecipe(CommentID),
	Reply nvarchar(50)
)

create table Follower (
	FollowedId int not null references Users(UserID),
	FollowerId int not null references Users(UserID)
)

create table Step (
	StepID int identity(1,1) primary key,
	StepDescription nvarchar(300),
	RecipeID int not null references Recipe(RecipeID),
)

create table IngredientCategory (
	IngredientCategoryID int identity(1,1) primary key,
	IngredientCategoryName nvarchar(100) not null
)


create table Ingredient (
	IngredientID int identity(1,1) primary key,
	IngredientName nvarchar(50) not null,
)

create table IngredientUsed (
	IngredientID int not null references Ingredient(IngredientID),
	RecipeID int not null references Recipe(RecipeID),
	primary key (RecipeID,IngredientID)
)

create table StoreAvailabe (
	SpecificAvailID int identity(1,1) primary key,
	StoreID int not null references Users(UserID),
	IngredientID int not null references Ingredient(IngredientID),
	Quantity int not null,
	IngredientImg nvarchar(100),
	Price int,
	SaleStatus int,
	SalePercentage int,
	IngredientCategoryID int not null references IngredientCategory(IngredientCategoryID),
	IngredientAddedDay date
)

create table PastPrice (
	ID int identity(1,1) primary key,
	SpecificAvailID int references StoreAvailabe(SpecificAvailID),
	PastPrice int
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
	SpecificAvailID int not null references StoreAvailabe(SpecificAvailID),
	OrderID int not null references Orders(OrderID),
	Quantity int not null 
)

insert into Users values ('Nguyen Ngoc Thinh', 'thinh@gmail.com', 'thinhse', '0987654321', '2021-02-22', 'Quang Nam', 'Male', 'ADMIN', 1, '', '')
insert into Users values ('Nguyen Quoc Manh', 'manh@gmail.com', 'manhse', '0987654321', '2021-02-21', 'Binh Dinh', 'Male', 'STORE', 1, '', '')
insert into Users values ('Nguyen Ba Nhat Tai', 'tai@gmail.com', 'taise', '0987654321', '2021-02-20', 'Ben Tre', 'Male', 'USER', 1, '', '')
insert into Users values ('Nguyen Hoang Huy', 'huy@gmail.com', 'huyse', '0987654321', '2021-02-23', 'Sai Gon', 'Male', 'USER', 1, '', '')
insert into Users values ('Tran Van Thien', 'thien@gmail.com', 'thiense', '0987654321', '2021-02-24', 'Quang Nam', 'Male', 'USER', 1, '', '')
-- 5 newest display store
insert into Users values ('Tiem Banh Hoa Binh', 'hoabinhbaker@gmail.com', 'hbbaker', '0987654321', '2021-02-21', 'Binh Dinh', 'Male', 'STORE', 1, 'img/shop.jpg', '')
insert into Users values ('Tiem Cooking 4Ever', 'cookin4ever@gmail.com', 'cookin4ever', '0987654321', '2021-02-21', 'Binh Dinh', 'Female', 'STORE', 1, 'img/shop.jpg', '')
insert into Users values ('Tiem BDay Celeb', 'bdayceleb@gmail.com', 'bdayceleb', '0987654321', '2021-02-21', 'Binh Dinh', 'Female', 'STORE', 1, 'img/shop.jpg', '')
insert into Users values ('Tiem Banh Hong Hoa', 'honghoabakery@gmail.com', 'honghoabakery', '0987654321', '2021-02-21', 'Binh Dinh', 'Female', 'STORE', 1, 'img/shop.jpg', '')
insert into Users values ('Tiem Banh Nhan Ai', 'nhanai@gmail.com', 'nhanai123', '0987654321', '2021-02-21', 'Binh Dinh', 'Male', 'STORE', 1, 'img/shop.jpg', '')

insert into Category values ('Cake')
insert into Category values ('Biscuit')
insert into Category values ('Salty Cake')

insert into Country values ('Viet Nam')
insert into Country values ('France')
insert into Country values ('USA')

-- 5 newest display store
insert into Recipe values ('3', 'Crepe', 'So Delicious', '2021-02-21', '', '30', '40', '3', 'img/crepe.jpg', '1', '1')
insert into Recipe values ('3', 'Waffle', 'Pretty Good', '2021-02-21', '', '60', '50', '4', 'img/waffe.jpg', '1', '2')
insert into Recipe values ('3', 'Croissant', 'Okay to eat', '2021-02-21', '', '30', '40', '3', 'img/croissant.jpg', '1', '1')
insert into Recipe values ('3', 'Cheese Cake', 'Yummy', '2021-02-21', '', '60', '50', '4', 'img/Cheese Cake.jpg', '1', '2')
insert into Recipe values ('3', 'Sandwich', 'Juicy with jams', '2021-02-21', '', '30', '40', '3', 'img/Sandwich.jpg', '1', '1')

insert into Step values ('Prepared the ingredients', '1')
insert into Step values ('Mix those up', '1')
insert into Step values ('Cook in the total time', '1')

insert into CommentRecipe values ('1', '4', 'Not bad at all, all my friends love it!', '5', '')
insert into CommentRecipe values ('2', '5', 'The ingredients is not healthy in my opinion', '1', '')
insert into CommentRecipe values ('3', '4', 'Not bad at all, all my friends love it!', '4', '')	
insert into CommentRecipe values ('4', '5', 'The ingredients is not healthy in my opinion', '3', '')
insert into CommentRecipe values ('5', '4', 'Not bad at all, all my friends love it!', '2', '')
insert into CommentRecipe values ('1', '5', 'The ingredients is not healthy in my opinion', '0', '')

insert into SavedRecipe values ('4', '1')
insert into SavedRecipe values ('5', '1')

insert into IngredientCategory values ('France')
insert into IngredientCategory values ('Ong Thinh Ngon')

insert into Ingredient values ('Flour')
insert into Ingredient values ('Egg')
insert into Ingredient values ('Baking Soda')
insert into Ingredient values ('Strawberry')

insert into StoreAvailabe values ('2', '1', '200', '', '200', '1', '5', '1', '')
insert into StoreAvailabe values ('2', '2', '100', '', '100', '1', '10', '2', '')
insert into StoreAvailabe values ('2', '3', '50', '', '300', '1', '2', '1', '')

insert into Orders values ('2021-02-21', '', '1', '3')
insert into Orders values ('2021-02-21', '', '1', '4')
insert into Orders values ('2021-02-21', '', '1', '5')

insert into OrderDetail values ('1', '1', '10')
insert into OrderDetail values ('2', '1', '20')
insert into OrderDetail values ('3', '1', '30')

insert into BlogCategory values ('Experience')
insert into BlogCategory values ('Knowledge')

-- 5 newest display store
insert into Blog values ('3', 'img/thermomix.png', '2021-02-21', 'Best Cooking Machine', 'It makes cooking easier than ever before', '', 'Its an flour mixer!', 1)
insert into Blog values ('4', 'img/thermomix.png', '2021-02-21', 'Making You In Love With Cooking', 'A great machine, help you to cook anything', '', 'Its an oven', 1)
insert into Blog values ('3', 'img/thermomix.png', '2021-02-21', 'Drying Machine', 'Dry Food everywhere!', '', 'Its an flour mixer!', 1)
insert into Blog values ('4', 'img/thermomix.png', '2021-02-21', 'Temperature Calculator', 'Perfect steak, why not?', '', 'Its an oven', 2)
insert into Blog values ('3', 'img/thermomix.png', '2021-02-21', 'Ultimate Cooker', 'Delicious meal everyday', '', 'Its an flour mixer!', 2)

insert into IngredientUsed values ('1', '1')
insert into IngredientUsed values ('2', '1')
insert into IngredientUsed values ('3', '1')

insert into CommentRecipe values ('1', '6', 'Not bad at all, all my friends love it!', '5', '')
insert into CommentRecipe values ('1', '7', 'The statements is false', '1', '')
insert into CommentRecipe values ('1', '8', 'It really helpful for me', '4', '')	
insert into CommentRecipe values ('1', '9', 'Pretty good but there are still something not right', '3', '')
insert into CommentRecipe values ('1', '10', 'Ridiculous!', '2', '')

insert into Badge values ('')

insert into BadgeReward values ('', '')

insert into PastPrice values ('', '')











