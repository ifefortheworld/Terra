
/** authority indexes **/
db.getCollection("authority").ensureIndex({
  "_id": NumberInt(1)
},[
  
]);

/** authority indexes **/
db.getCollection("authority").ensureIndex({
  "authority": NumberInt(1)
},{
  "unique": true
});

/** comment indexes **/
db.getCollection("comment").ensureIndex({
  "_id": NumberInt(1)
},[
  
]);

/** comment indexes **/
db.getCollection("comment").ensureIndex({
  "owner": NumberInt(1)
},[
  
]);

/** comment indexes **/
db.getCollection("comment").ensureIndex({
  "fileId": NumberInt(1)
},[
  
]);

/** file indexes **/
db.getCollection("file").ensureIndex({
  "_id": NumberInt(1)
},[
  
]);

/** file indexes **/
db.getCollection("file").ensureIndex({
  "ownerId": NumberInt(1)
},[
  
]);

/** file indexes **/
db.getCollection("file").ensureIndex({
  "fileUrl": NumberInt(1)
},[
  
]);

/** realFile indexes **/
db.getCollection("realFile").ensureIndex({
  "_id": NumberInt(1)
},[
  
]);

/** role indexes **/
db.getCollection("role").ensureIndex({
  "_id": NumberInt(1)
},[
  
]);

/** role indexes **/
db.getCollection("role").ensureIndex({
  "role": NumberInt(1)
},{
  "unique": true
});

/** sourceFile indexes **/
db.getCollection("sourceFile").ensureIndex({
  "_id": NumberInt(1)
},[
  
]);

/** sourceFile indexes **/
db.getCollection("sourceFile").ensureIndex({
  "fileHashCode": NumberInt(1)
},[
  
]);

/** system.js indexes **/
db.getCollection("system.js").ensureIndex({
  "_id": NumberInt(1)
},[
  
]);

/** tag indexes **/
db.getCollection("tag").ensureIndex({
  "_id": NumberInt(1)
},[
  
]);

/** tag indexes **/
db.getCollection("tag").ensureIndex({
  "name": NumberInt(1)
},{
  "unique": true
});

/** terraFile indexes **/
db.getCollection("terraFile").ensureIndex({
  "_id": NumberInt(1)
},[
  
]);

/** terraFile indexes **/
db.getCollection("terraFile").ensureIndex({
  "owner": NumberInt(1)
},[
  
]);

/** terraFile indexes **/
db.getCollection("terraFile").ensureIndex({
  "ownerId": NumberInt(1)
},[
  
]);

/** terraFile indexes **/
db.getCollection("terraFile").ensureIndex({
  "fileUrl": NumberInt(1)
},[
  
]);

/** user indexes **/
db.getCollection("user").ensureIndex({
  "_id": NumberInt(1)
},[
  
]);

/** user indexes **/
db.getCollection("user").ensureIndex({
  "username": NumberInt(1)
},{
  "unique": true
});

/** authority records **/
db.getCollection("authority").insert({
  "_class": "com.ireland.model.Authority",
  "_id": ObjectId("50deed7672594e11731e0b34"),
  "authority": "index",
  "description": "访问主页",
  "enable": true,
  "urlPattern": "\/myspace\/*"
});
db.getCollection("authority").insert({
  "_id": ObjectId("5134a7b44316de5031000000"),
  "_class": "com.ireland.model.Authority",
  "authority": "file-upload",
  "description": "上传文件",
  "enable": true,
  "urlPattern": "\/file\/upload"
});
db.getCollection("authority").insert({
  "_id": ObjectId("5134a7b44316de5061000000"),
  "_class": "com.ireland.model.Authority",
  "authority": "file-coments-upload",
  "description": "文件评论上传",
  "enable": true,
  "urlPattern": "\/files\/*\/coments"
});
db.getCollection("authority").insert({
  "_id": ObjectId("5198e7028906f39829000000"),
  "_class": "com.ireland.model.Authority",
  "authority": "index2",
  "description": "访问主页2",
  "enable": true,
  "urlPattern": "\/myspace"
});

/** comment records **/
db.getCollection("comment").insert({
  "_id": ObjectId("512e0578c91b8af834f9cc37"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "admin",
  "fileId": "512db945c91b4daa738ce8f9",
  "content": "Not Bad!",
  "date": ISODate("2013-02-27T13:09:11.959Z"),
  "votes": NumberInt(0)
});
db.getCollection("comment").insert({
  "_id": ObjectId("512e0833c91b8af834f9cc38"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "admin",
  "fileId": "512db945c91b4daa738ce8f9",
  "content": "Very Good!",
  "date": ISODate("2013-02-27T13:20:51.969Z"),
  "votes": NumberInt(8)
});
db.getCollection("comment").insert({
  "_id": ObjectId("512e08b2c91b8af834f9cc39"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "admin",
  "fileId": "512db945c91b4daa738ce8f9",
  "content": "Weel!",
  "date": ISODate("2013-02-27T13:22:58.239Z"),
  "votes": NumberInt(0)
});
db.getCollection("comment").insert({
  "_id": ObjectId("512e08c7c91b8af834f9cc3a"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "admin",
  "fileId": "512db945c91b4daa738ce8f9",
  "content": "Just So So!",
  "date": ISODate("2013-02-27T13:23:19.742Z"),
  "votes": NumberInt(11)
});
db.getCollection("comment").insert({
  "_id": ObjectId("512e08e3c91b8af834f9cc3b"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "admin",
  "fileId": "512db945c91b4daa738ce8f9",
  "content": "So Bad!",
  "date": ISODate("2013-02-27T13:23:47.862Z"),
  "votes": NumberInt(0)
});
db.getCollection("comment").insert({
  "_id": ObjectId("512e1db5c91b13005cab6ea0"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "admin",
  "fileId": "512db93fc91b4daa738ce8f8",
  "content": "Not Bad!",
  "date": ISODate("2013-02-27T14:52:37.441Z"),
  "votes": NumberInt(3)
});
db.getCollection("comment").insert({
  "_id": ObjectId("512e1dc1c91b13005cab6ea1"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "admin",
  "fileId": "512db93fc91b4daa738ce8f8",
  "content": "Good!",
  "date": ISODate("2013-02-27T14:52:49.141Z"),
  "votes": NumberInt(0)
});
db.getCollection("comment").insert({
  "_id": ObjectId("512e1dc9c91b13005cab6ea2"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "admin",
  "fileId": "512db93fc91b4daa738ce8f8",
  "content": "Very Good!",
  "date": ISODate("2013-02-27T14:52:57.908Z"),
  "votes": NumberInt(4)
});
db.getCollection("comment").insert({
  "_id": ObjectId("512f5292c91bf9f6a1a8cdd7"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "admin",
  "fileId": "512f1075c91bae835f85fe0a",
  "content": "Good!",
  "date": ISODate("2013-02-28T12:50:26.304Z"),
  "votes": NumberInt(1)
});
db.getCollection("comment").insert({
  "_id": ObjectId("512f529cc91bf9f6a1a8cdd8"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "admin",
  "fileId": "512f1075c91bae835f85fe0a",
  "content": "Best!",
  "date": ISODate("2013-02-28T12:50:36.143Z"),
  "votes": NumberInt(4)
});
db.getCollection("comment").insert({
  "_id": ObjectId("512f532cc91bf9f6a1a8cdd9"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "admin",
  "fileId": "512f1075c91bae835f85fe0a",
  "content": "Very Good!",
  "date": ISODate("2013-02-28T12:53:00.565Z"),
  "votes": NumberInt(2)
});
db.getCollection("comment").insert({
  "_id": ObjectId("512f5c76c91b9d40806a4d9d"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "admin",
  "fileId": "512f1075c91bae835f85fe0a",
  "content": "Very Good!!!!",
  "date": ISODate("2013-02-28T13:32:38.498Z"),
  "votes": NumberInt(2)
});
db.getCollection("comment").insert({
  "_id": ObjectId("51518c9d6491206605a3e5b6"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "admin",
  "fileId": "51345d102e718ca4eb7af282",
  "content": "sadfdsf",
  "date": ISODate("2013-03-26T11:55:08.998Z"),
  "votes": NumberInt(0)
});
db.getCollection("comment").insert({
  "_id": ObjectId("5151b8f0649163cb421da20b"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "admin",
  "fileId": "512db91fc91b4daa738ce8f4",
  "content": "Not Bad!",
  "date": ISODate("2013-03-26T15:04:16.508Z"),
  "votes": NumberInt(1)
});
db.getCollection("comment").insert({
  "_id": ObjectId("5198c893884946f8dbbd36fb"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "admin",
  "fileId": "51989fa788499cc56de9adba",
  "content": "good",
  "date": ISODate("2013-05-19T12:41:55.770Z"),
  "votes": NumberInt(5)
});
db.getCollection("comment").insert({
  "_id": ObjectId("5198c89a884946f8dbbd36fc"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "admin",
  "fileId": "51989fa788499cc56de9adba",
  "content": "best",
  "date": ISODate("2013-05-19T12:42:02.434Z"),
  "votes": NumberInt(3)
});
db.getCollection("comment").insert({
  "_id": ObjectId("5198c8a2884946f8dbbd36fd"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "admin",
  "fileId": "51989fa788499cc56de9adba",
  "content": "vwee",
  "date": ISODate("2013-05-19T12:42:10.344Z"),
  "votes": NumberInt(3)
});
db.getCollection("comment").insert({
  "_id": ObjectId("5198c8a7884946f8dbbd36fe"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "admin",
  "fileId": "51989fa788499cc56de9adba",
  "content": "ggg",
  "date": ISODate("2013-05-19T12:42:15.835Z"),
  "votes": NumberInt(2)
});
db.getCollection("comment").insert({
  "_id": ObjectId("5198d8ea8849de74a5a5b2e8"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "admin",
  "fileId": "5198d84a8849de74a5a5b2e6",
  "content": "abc",
  "date": ISODate("2013-05-19T13:51:38.786Z"),
  "votes": NumberInt(0)
});
db.getCollection("comment").insert({
  "_id": ObjectId("5198d8f18849de74a5a5b2e9"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "admin",
  "fileId": "5198d84a8849de74a5a5b2e6",
  "content": "good",
  "date": ISODate("2013-05-19T13:51:45.829Z"),
  "votes": NumberInt(0)
});
db.getCollection("comment").insert({
  "_id": ObjectId("5198d9318849de74a5a5b2ea"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "admin",
  "fileId": "5198d84a8849de74a5a5b2e6",
  "content": "beste",
  "date": ISODate("2013-05-19T13:52:49.234Z"),
  "votes": NumberInt(0)
});
db.getCollection("comment").insert({
  "_id": ObjectId("5198d9418849de74a5a5b2eb"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "admin",
  "fileId": "5198d84a8849de74a5a5b2e6",
  "content": "best",
  "date": ISODate("2013-05-19T13:53:05.760Z"),
  "votes": NumberInt(0)
});
db.getCollection("comment").insert({
  "_id": ObjectId("5198e30c8849c65c58bc74c3"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "admin",
  "fileId": "51989fa788499cc56de9adba",
  "content": "good",
  "date": ISODate("2013-05-19T14:34:52.803Z"),
  "votes": NumberInt(0)
});
db.getCollection("comment").insert({
  "_id": ObjectId("519a39268849966bbc3961be"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "admin",
  "fileId": "5198e3928849c65c58bc74c4",
  "content": "jack",
  "date": ISODate("2013-05-20T14:54:30.950Z"),
  "votes": NumberInt(5)
});
db.getCollection("comment").insert({
  "_id": ObjectId("519a39c58849966bbc3961bf"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "business",
  "fileId": "5198e3928849c65c58bc74c4",
  "content": "best",
  "date": ISODate("2013-05-20T14:57:09.517Z"),
  "votes": NumberInt(2)
});
db.getCollection("comment").insert({
  "_id": ObjectId("519b819b9737597821f8e413"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "admin",
  "fileId": "519a3b0a8849966bbc3961c0",
  "content": "啦",
  "date": ISODate("2013-05-21T14:15:55.146Z"),
  "votes": NumberInt(25)
});
db.getCollection("comment").insert({
  "_id": ObjectId("519b8d9a9737597821f8e414"),
  "_class": "com.ireland.model.business.Comment",
  "owner": "admin",
  "fileId": "519a3e388849966bbc3961c2",
  "content": "fdsafds",
  "date": ISODate("2013-05-21T15:07:06.712Z"),
  "votes": NumberInt(3)
});

/** file records **/
db.getCollection("file").insert({
  "_class": "com.ireland.model.business.File",
  "_id": ObjectId("51989fa788499cc56de9adba"),
  "comments": [
    
  ],
  "detail": "I love this song",
  "downsCnt": NumberInt(3),
  "favsCnt": NumberInt(0),
  "fileOriginalName": "0.mp3",
  "fileUrl": "\/staticfiles\/1ef1a8b5-b8d7-4fa1-a45d-4d910482ff98.mp3",
  "isShared": false,
  "name": "Music One",
  "owner": "admin",
  "ownerId": "50dfaf31636b4356f5addf1d",
  "sourceFileId": "51989fa788499cc56de9adbb",
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("5155595d496537d424d70680")
    },
    {
      "$ref": "tag",
      "$id": ObjectId("51345c172e718ca4eb7af281")
    }
  ],
  "type": "Text",
  "uploadDate": ISODate("2013-05-19T09:47:19.593Z"),
  "viewsCnt": NumberInt(49)
});
db.getCollection("file").insert({
  "_class": "com.ireland.model.business.File",
  "_id": ObjectId("5198d84a8849de74a5a5b2e6"),
  "comments": [
    
  ],
  "detail": "afdsf",
  "downsCnt": NumberInt(3),
  "favsCnt": NumberInt(0),
  "fileOriginalName": "808_2013-02-25.nbu",
  "fileUrl": "\/staticfiles\/0f9d6a72-35d3-4ee3-9c79-de8dee9f7c76.nbu",
  "name": "808 nub",
  "owner": "admin",
  "ownerId": "50dfaf31636b4356f5addf1d",
  "sourceFileId": "5198d84a8849de74a5a5b2e7",
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("5198d8428849de74a5a5b2e5")
    }
  ],
  "type": "Audio",
  "uploadDate": ISODate("2013-05-19T13:48:52.267Z"),
  "viewsCnt": NumberInt(14)
});
db.getCollection("file").insert({
  "_class": "com.ireland.model.business.File",
  "_id": ObjectId("5198da538849de74a5a5b2ec"),
  "comments": [
    
  ],
  "detail": "asdfdsakf",
  "downsCnt": NumberInt(4),
  "favsCnt": NumberInt(0),
  "fileOriginalName": "0.mp3",
  "fileUrl": "\/staticfiles\/47ca5e5b-b1ce-4464-b5ac-fc4cabd45c61.mp3",
  "isShared": false,
  "name": "sfdsaf",
  "owner": "admin",
  "ownerId": "50dfaf31636b4356f5addf1d",
  "sourceFileId": "5198da538849de74a5a5b2ed",
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("5198d8428849de74a5a5b2e5")
    }
  ],
  "type": "Audio",
  "uploadDate": ISODate("2013-05-19T13:57:39.150Z"),
  "viewsCnt": NumberInt(6)
});
db.getCollection("file").insert({
  "_class": "com.ireland.model.business.File",
  "_id": ObjectId("5198e3928849c65c58bc74c4"),
  "comments": [
    
  ],
  "detail": "Game Game jack",
  "downsCnt": NumberInt(1),
  "favsCnt": NumberInt(0),
  "fileOriginalName": "大学物理.rar",
  "fileUrl": "\/staticfiles\/1742593e-7307-4376-83b8-f82eae5cdd73.rar",
  "isShared": true,
  "name": "Game",
  "owner": "admin",
  "ownerId": "50dfaf31636b4356f5addf1d",
  "sourceFileId": "5198e3928849c65c58bc74c5",
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("5198d8428849de74a5a5b2e5")
    }
  ],
  "type": "Image",
  "uploadDate": ISODate("2013-05-19T14:37:06.687Z"),
  "viewsCnt": NumberInt(16)
});
db.getCollection("file").insert({
  "_class": "com.ireland.model.business.File",
  "_id": ObjectId("519a3b0a8849966bbc3961c0"),
  "comments": [
    
  ],
  "detail": "some thing i love",
  "downsCnt": NumberInt(0),
  "favsCnt": NumberInt(0),
  "fileOriginalName": "2012-2013-2重修上课安排.xls",
  "fileUrl": "\/staticfiles\/0969d28d-e9f9-4bd8-8d7c-77dd8517c5bb.xls",
  "isShared": true,
  "name": "game music",
  "owner": "business",
  "ownerId": "50dfb22c636b4356f5addf1e",
  "sourceFileId": "519a3b0a8849966bbc3961c1",
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("5198d8428849de74a5a5b2e5")
    }
  ],
  "type": "Other",
  "uploadDate": ISODate("2013-05-20T15:02:34.621Z"),
  "viewsCnt": NumberInt(7)
});
db.getCollection("file").insert({
  "_class": "com.ireland.model.business.File",
  "_id": ObjectId("519a3e388849966bbc3961c2"),
  "comments": [
    
  ],
  "detail": "\"Final Fantasy\" was originally a video game software company by the Japanese Square, it is one of the best-selling series of video games from Nintendo on the \"Final Fantasy\" generation has already gone through more than 20 yearshistory.",
  "downsCnt": NumberInt(1),
  "favsCnt": NumberInt(0),
  "fileOriginalName": "JAVA组招新名单.xls",
  "fileUrl": "\/staticfiles\/0e8b5ef2-f3c9-4ad3-a85a-35cf1e61f792.xls",
  "isShared": true,
  "name": "Final Fantasy Game&Music",
  "owner": "business",
  "ownerId": "50dfb22c636b4356f5addf1e",
  "sourceFileId": "519a3e388849966bbc3961c3",
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("5198d8428849de74a5a5b2e5")
    }
  ],
  "type": "Other",
  "uploadDate": ISODate("2013-05-20T15:16:08.620Z"),
  "viewsCnt": NumberInt(4)
});
db.getCollection("file").insert({
  "_class": "com.ireland.model.business.File",
  "_id": ObjectId("519a3f128849966bbc3961c6"),
  "comments": [
    
  ],
  "detail": "Wanderful mp3",
  "downsCnt": NumberInt(0),
  "favsCnt": NumberInt(0),
  "fileOriginalName": "0.mp3",
  "fileUrl": "\/staticfiles\/7ec53153-8b18-419d-a008-bbd94cd08db2.mp3",
  "isShared": true,
  "name": "Wanderful mp3",
  "owner": "business",
  "ownerId": "50dfb22c636b4356f5addf1e",
  "sourceFileId": "519a3f128849966bbc3961c7",
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("519a3f128849966bbc3961c4")
    },
    {
      "$ref": "tag",
      "$id": ObjectId("519a3f128849966bbc3961c5")
    }
  ],
  "type": "Audio",
  "uploadDate": ISODate("2013-05-20T15:19:46.249Z"),
  "viewsCnt": NumberInt(0)
});
db.getCollection("file").insert({
  "_class": "com.ireland.model.business.File",
  "_id": ObjectId("51a4a7d29737692ee418b8a4"),
  "comments": [
    
  ],
  "detail": "Netty-A hight performance sever",
  "downsCnt": NumberInt(0),
  "favsCnt": NumberInt(0),
  "fileOriginalName": "netty.png",
  "fileUrl": "\/staticfiles\/914ade0d-ef1d-4b06-b109-2088fea62220.png",
  "isShared": true,
  "name": "Netty-A hight performance sever",
  "owner": "admin",
  "ownerId": "50dfaf31636b4356f5addf1d",
  "sourceFileId": "51a4a7d29737692ee418b8a5",
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("51a4a7d29737692ee418b8a2")
    },
    {
      "$ref": "tag",
      "$id": ObjectId("51a4a7d29737692ee418b8a3")
    }
  ],
  "type": "Other",
  "uploadDate": ISODate("2013-05-28T12:49:22.504Z"),
  "viewsCnt": NumberInt(0)
});
db.getCollection("file").insert({
  "_class": "com.ireland.model.business.File",
  "_id": ObjectId("51a58dc69737da48c6635d5c"),
  "comments": [
    
  ],
  "detail": "",
  "downsCnt": NumberInt(1),
  "favsCnt": NumberInt(0),
  "fileOriginalName": "image.jpg",
  "fileUrl": "\/staticfiles\/9d5b4f98-4f65-4cf9-bdb6-e8ffa7271591.jpg",
  "isShared": true,
  "name": "Ken",
  "owner": "admin",
  "ownerId": "50dfaf31636b4356f5addf1d",
  "sourceFileId": "51a58dc69737da48c6635d5d",
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("5134af002e71761ac39329df")
    }
  ],
  "type": "Image",
  "uploadDate": ISODate("2013-05-29T05:10:30.664Z"),
  "viewsCnt": NumberInt(0)
});
db.getCollection("file").insert({
  "_class": "com.ireland.model.business.File",
  "_id": ObjectId("51a58f6c9737da48c6635d5e"),
  "comments": [
    
  ],
  "detail": "",
  "downsCnt": NumberInt(1),
  "favsCnt": NumberInt(0),
  "fileOriginalName": "image.jpg",
  "fileUrl": "\/staticfiles\/5506f2d8-cf89-41e1-901e-37d12c081f0b.jpg",
  "isShared": true,
  "name": "Duhai",
  "owner": "admin",
  "ownerId": "50dfaf31636b4356f5addf1d",
  "sourceFileId": "51a58f6c9737da48c6635d5f",
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("5134af002e71761ac39329df")
    }
  ],
  "type": "Text",
  "uploadDate": ISODate("2013-05-29T05:17:32.194Z"),
  "viewsCnt": NumberInt(0)
});
db.getCollection("file").insert({
  "_class": "com.ireland.model.business.File",
  "_id": ObjectId("51a5937a9737da48c6635d60"),
  "comments": [
    
  ],
  "detail": "",
  "downsCnt": NumberInt(2),
  "favsCnt": NumberInt(0),
  "fileOriginalName": "trim.IEscel.MOV",
  "fileUrl": "\/staticfiles\/e92cee6c-4929-4122-80ea-cead3cd597af.MOV",
  "isShared": true,
  "name": "Duhai2",
  "owner": "admin",
  "ownerId": "50dfaf31636b4356f5addf1d",
  "sourceFileId": "51a5937a9737da48c6635d61",
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("5134af002e71761ac39329df")
    }
  ],
  "type": "Video",
  "uploadDate": ISODate("2013-05-29T05:34:50.0Z"),
  "viewsCnt": NumberInt(0)
});

/** realFile records **/
db.getCollection("realFile").insert({
  "_id": ObjectId("5155595d496537d424d70681"),
  "_class": "com.ireland.model.business.RealFile",
  "storageLocation": "\/staticfiles\/5465aa19-9e4a-46fe-9298-18892f05d518",
  "referenceCount": NumberInt(1),
  "referenceIds": [
    "5155595d496537d424d70682"
  ]
});

/** role records **/
db.getCollection("role").insert({
  "_class": "com.ireland.model.Role",
  "_id": ObjectId("50def2217259ccb5db904a17"),
  "authorities": [
    {
      "$ref": "authority",
      "$id": ObjectId("50deed7672594e11731e0b34")
    },
    {
      "$ref": "authority",
      "$id": ObjectId("5134a7b44316de5031000000")
    }
  ],
  "enable": true,
  "name": "领导",
  "role": "ROLE_LEADER"
});
db.getCollection("role").insert({
  "_class": "com.ireland.model.Role",
  "_id": ObjectId("50def20672599d3f0bbb9bee"),
  "authorities": [
    {
      "$ref": "authority",
      "$id": ObjectId("50deed7672594e11731e0b34")
    },
    {
      "$ref": "authority",
      "$id": ObjectId("5134a7b44316de5031000000")
    }
  ],
  "enable": true,
  "name": "会计",
  "role": "ROLE_ACCOUNTANT"
});
db.getCollection("role").insert({
  "_class": "com.ireland.model.Role",
  "_id": ObjectId("50def1d67259f8b22c7493b1"),
  "authorities": [
    {
      "$ref": "authority",
      "$id": ObjectId("50deed7672594e11731e0b34")
    },
    {
      "$ref": "authority",
      "$id": ObjectId("5134a7b44316de5031000000")
    }
  ],
  "enable": true,
  "name": "财务",
  "role": "ROLE_FINANCE"
});
db.getCollection("role").insert({
  "_class": "com.ireland.model.Role",
  "_id": ObjectId("50def1897259b9d64facec90"),
  "authorities": [
    {
      "$ref": "authority",
      "$id": ObjectId("50deed7672594e11731e0b34")
    },
    {
      "$ref": "authority",
      "$id": ObjectId("5134a7b44316de5031000000")
    }
  ],
  "enable": true,
  "name": "调度",
  "role": "ROLE_SCHEDULE"
});
db.getCollection("role").insert({
  "_class": "com.ireland.model.Role",
  "_id": ObjectId("50def11f7259628f6d93888e"),
  "authorities": [
    {
      "$ref": "authority",
      "$id": ObjectId("50deed7672594e11731e0b34")
    },
    {
      "$ref": "authority",
      "$id": ObjectId("5134a7b44316de5031000000")
    }
  ],
  "enable": true,
  "name": "业务",
  "role": "ROLE_BUSINESS"
});
db.getCollection("role").insert({
  "_class": "com.ireland.model.Role",
  "_id": ObjectId("50deec777259ea7d80c6fab3"),
  "authorities": [
    {
      "$ref": "authority",
      "$id": ObjectId("50deed7672594e11731e0b34")
    },
    {
      "$ref": "authority",
      "$id": ObjectId("5134a7b44316de5031000000")
    },
    {
      "$ref": "authority",
      "$id": ObjectId("5134a7b44316de5061000000")
    },
    {
      "$ref": "authority",
      "$id": ObjectId("5198e7028906f39829000000")
    }
  ],
  "enable": true,
  "name": "管理员",
  "role": "ROLE_ADMIN"
});

/** sourceFile records **/
db.getCollection("sourceFile").insert({
  "_id": ObjectId("51989fa788499cc56de9adbb"),
  "_class": "com.ireland.model.business.SourceFile",
  "storageLocation": "\/staticfiles\/1ef1a8b5-b8d7-4fa1-a45d-4d910482ff98",
  "size": NumberLong(4685576),
  "fileCount": NumberInt(1),
  "fileIds": [
    "51989fa788499cc56de9adba"
  ]
});
db.getCollection("sourceFile").insert({
  "_id": ObjectId("5198d84a8849de74a5a5b2e7"),
  "_class": "com.ireland.model.business.SourceFile",
  "storageLocation": "\/staticfiles\/0f9d6a72-35d3-4ee3-9c79-de8dee9f7c76",
  "size": NumberLong(3297170),
  "fileCount": NumberInt(1),
  "fileIds": [
    "5198d84a8849de74a5a5b2e6"
  ]
});
db.getCollection("sourceFile").insert({
  "_id": ObjectId("5198da538849de74a5a5b2ed"),
  "_class": "com.ireland.model.business.SourceFile",
  "storageLocation": "\/staticfiles\/47ca5e5b-b1ce-4464-b5ac-fc4cabd45c61",
  "size": NumberLong(4685576),
  "fileCount": NumberInt(1),
  "fileIds": [
    "5198da538849de74a5a5b2ec"
  ]
});
db.getCollection("sourceFile").insert({
  "_id": ObjectId("5198e3928849c65c58bc74c5"),
  "_class": "com.ireland.model.business.SourceFile",
  "storageLocation": "\/staticfiles\/1742593e-7307-4376-83b8-f82eae5cdd73",
  "size": NumberLong(2712289),
  "fileCount": NumberInt(1),
  "fileIds": [
    "5198e3928849c65c58bc74c4"
  ]
});
db.getCollection("sourceFile").insert({
  "_id": ObjectId("519a3b0a8849966bbc3961c1"),
  "_class": "com.ireland.model.business.SourceFile",
  "storageLocation": "\/staticfiles\/0969d28d-e9f9-4bd8-8d7c-77dd8517c5bb",
  "size": NumberLong(54784),
  "fileCount": NumberInt(1),
  "fileIds": [
    "519a3b0a8849966bbc3961c0"
  ]
});
db.getCollection("sourceFile").insert({
  "_id": ObjectId("519a3e388849966bbc3961c3"),
  "_class": "com.ireland.model.business.SourceFile",
  "storageLocation": "\/staticfiles\/0e8b5ef2-f3c9-4ad3-a85a-35cf1e61f792",
  "size": NumberLong(25600),
  "fileCount": NumberInt(1),
  "fileIds": [
    "519a3e388849966bbc3961c2"
  ]
});
db.getCollection("sourceFile").insert({
  "_id": ObjectId("519a3f128849966bbc3961c7"),
  "_class": "com.ireland.model.business.SourceFile",
  "storageLocation": "\/staticfiles\/7ec53153-8b18-419d-a008-bbd94cd08db2",
  "size": NumberLong(4685576),
  "fileCount": NumberInt(1),
  "fileIds": [
    "519a3f128849966bbc3961c6"
  ]
});
db.getCollection("sourceFile").insert({
  "_id": ObjectId("51a4a7d29737692ee418b8a5"),
  "_class": "com.ireland.model.business.SourceFile",
  "storageLocation": "\/staticfiles\/914ade0d-ef1d-4b06-b109-2088fea62220",
  "size": NumberLong(118822),
  "fileCount": NumberInt(1),
  "fileIds": [
    "51a4a7d29737692ee418b8a4"
  ]
});
db.getCollection("sourceFile").insert({
  "_id": ObjectId("51a58dc69737da48c6635d5d"),
  "_class": "com.ireland.model.business.SourceFile",
  "storageLocation": "\/staticfiles\/9d5b4f98-4f65-4cf9-bdb6-e8ffa7271591",
  "size": NumberLong(927249),
  "fileCount": NumberInt(1),
  "fileIds": [
    "51a58dc69737da48c6635d5c"
  ]
});
db.getCollection("sourceFile").insert({
  "_id": ObjectId("51a58f6c9737da48c6635d5f"),
  "_class": "com.ireland.model.business.SourceFile",
  "storageLocation": "\/staticfiles\/5506f2d8-cf89-41e1-901e-37d12c081f0b",
  "size": NumberLong(850948),
  "fileCount": NumberInt(1),
  "fileIds": [
    "51a58f6c9737da48c6635d5e"
  ]
});
db.getCollection("sourceFile").insert({
  "_id": ObjectId("51a5937a9737da48c6635d61"),
  "_class": "com.ireland.model.business.SourceFile",
  "storageLocation": "\/staticfiles\/e92cee6c-4929-4122-80ea-cead3cd597af",
  "size": NumberLong(592292),
  "fileCount": NumberInt(1),
  "fileIds": [
    "51a5937a9737da48c6635d60"
  ]
});

/** system.indexes records **/
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "_id": NumberInt(1)
  },
  "ns": "terra.authority",
  "name": "_id_"
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "authority": NumberInt(1)
  },
  "unique": true,
  "ns": "terra.authority",
  "name": "authority",
  "dropDups": false,
  "sparse": false
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "_id": NumberInt(1)
  },
  "ns": "terra.role",
  "name": "_id_"
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "role": NumberInt(1)
  },
  "unique": true,
  "ns": "terra.role",
  "name": "role",
  "dropDups": false,
  "sparse": false
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "_id": NumberInt(1)
  },
  "ns": "terra.user",
  "name": "_id_"
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "username": NumberInt(1)
  },
  "unique": true,
  "ns": "terra.user",
  "name": "username",
  "dropDups": false,
  "sparse": false
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "_id": NumberInt(1)
  },
  "ns": "terra.tag",
  "name": "_id_"
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "name": NumberInt(1)
  },
  "unique": true,
  "ns": "terra.tag",
  "name": "name",
  "dropDups": false,
  "sparse": false
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "_id": NumberInt(1)
  },
  "ns": "terra.terraFile",
  "name": "_id_"
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "_id": NumberInt(1)
  },
  "ns": "terra.comment",
  "name": "_id_"
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "owner": NumberInt(1)
  },
  "ns": "terra.terraFile",
  "name": "owner",
  "dropDups": false,
  "sparse": false
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "owner": NumberInt(1)
  },
  "ns": "terra.comment",
  "name": "owner",
  "dropDups": false,
  "sparse": false
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "fileId": NumberInt(1)
  },
  "ns": "terra.comment",
  "name": "fileId",
  "dropDups": false,
  "sparse": false
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "ownerId": NumberInt(1)
  },
  "ns": "terra.terraFile",
  "name": "ownerId",
  "dropDups": false,
  "sparse": false
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "_id": NumberInt(1)
  },
  "ns": "terra.realFile",
  "name": "_id_"
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "fileUrl": NumberInt(1)
  },
  "ns": "terra.terraFile",
  "name": "fileUrl",
  "dropDups": false,
  "sparse": false
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "_id": NumberInt(1)
  },
  "ns": "terra.file",
  "name": "_id_"
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "ownerId": NumberInt(1)
  },
  "ns": "terra.file",
  "name": "ownerId",
  "dropDups": false,
  "sparse": false
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "fileUrl": NumberInt(1)
  },
  "ns": "terra.file",
  "name": "fileUrl",
  "dropDups": false,
  "sparse": false
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "_id": NumberInt(1)
  },
  "ns": "terra.sourceFile",
  "name": "_id_"
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "fileHashCode": NumberInt(1)
  },
  "ns": "terra.sourceFile",
  "name": "fileHashCode",
  "dropDups": false,
  "sparse": false
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "_id": NumberInt(1)
  },
  "ns": "terra.system.js",
  "name": "_id_"
});

/** system.js records **/

/** tag records **/
db.getCollection("tag").insert({
  "_id": ObjectId("512c690bc91b86bbe3ccd5fd"),
  "_class": "com.ireland.model.business.Tag",
  "name": "Last Day",
  "fileCnt": NumberInt(2)
});
db.getCollection("tag").insert({
  "_id": ObjectId("512c6914c91b86bbe3ccd5fe"),
  "_class": "com.ireland.model.business.Tag",
  "name": "Sad",
  "fileCnt": NumberInt(2)
});
db.getCollection("tag").insert({
  "_id": ObjectId("512c6917c91b86bbe3ccd5ff"),
  "_class": "com.ireland.model.business.Tag",
  "name": "Movie",
  "fileCnt": NumberInt(3)
});
db.getCollection("tag").insert({
  "_id": ObjectId("512c6eb9c91b0b742f7ab9f3"),
  "_class": "com.ireland.model.business.Tag",
  "name": "War",
  "fileCnt": NumberInt(2)
});
db.getCollection("tag").insert({
  "_id": ObjectId("512c6edcc91b0b742f7ab9f4"),
  "_class": "com.ireland.model.business.Tag",
  "name": "Game",
  "fileCnt": NumberInt(2)
});
db.getCollection("tag").insert({
  "_id": ObjectId("512c924ac91b85218640c80f"),
  "_class": "com.ireland.model.business.Tag",
  "name": "handsome",
  "fileCnt": NumberInt(1)
});
db.getCollection("tag").insert({
  "_id": ObjectId("512c924ac91b85218640c810"),
  "_class": "com.ireland.model.business.Tag",
  "name": "man",
  "fileCnt": NumberInt(1)
});
db.getCollection("tag").insert({
  "_id": ObjectId("512cbaefc91b9624dd17ba56"),
  "_class": "com.ireland.model.business.Tag",
  "name": "Jennifer Lopez",
  "fileCnt": NumberInt(1)
});
db.getCollection("tag").insert({
  "_id": ObjectId("512cbaf1c91b9624dd17ba57"),
  "_class": "com.ireland.model.business.Tag",
  "name": "On The Floor",
  "fileCnt": NumberInt(1)
});
db.getCollection("tag").insert({
  "_id": ObjectId("512ccaf8c91bebd24ec9375d"),
  "_class": "com.ireland.model.business.Tag",
  "name": "One Day",
  "fileCnt": NumberInt(1)
});
db.getCollection("tag").insert({
  "_id": ObjectId("512d5fb6c91b082db7272d0d"),
  "_class": "com.ireland.model.business.Tag",
  "name": "Lukie D",
  "fileCnt": NumberInt(10)
});
db.getCollection("tag").insert({
  "_id": ObjectId("512d5fb6c91b082db7272d0e"),
  "_class": "com.ireland.model.business.Tag",
  "name": "Love Again",
  "fileCnt": NumberInt(9)
});
db.getCollection("tag").insert({
  "_id": ObjectId("512d6ebbc91bf435ecb88bc8"),
  "_class": "com.ireland.model.business.Tag",
  "name": "Love",
  "fileCnt": NumberInt(9)
});
db.getCollection("tag").insert({
  "_id": ObjectId("512f09e7c91b620384056328"),
  "_class": "com.ireland.model.business.Tag",
  "name": "TMS",
  "fileCnt": NumberInt(3)
});
db.getCollection("tag").insert({
  "_id": ObjectId("51345c172e718ca4eb7af281"),
  "_class": "com.ireland.model.business.Tag",
  "name": "Song",
  "fileCnt": NumberInt(2)
});
db.getCollection("tag").insert({
  "_id": ObjectId("513498222e715b7d5062f318"),
  "_class": "com.ireland.model.business.Tag",
  "name": "Cool",
  "fileCnt": NumberInt(1)
});
db.getCollection("tag").insert({
  "_id": ObjectId("51349a332e71477e602e3dc2"),
  "_class": "com.ireland.model.business.Tag",
  "name": "YES",
  "fileCnt": NumberInt(2)
});
db.getCollection("tag").insert({
  "_id": ObjectId("5134a8e92e71bc6d92a143b1"),
  "_class": "com.ireland.model.business.Tag",
  "name": "News",
  "fileCnt": NumberInt(1)
});
db.getCollection("tag").insert({
  "_id": ObjectId("5134adfa2e71ac1b004281d9"),
  "_class": "com.ireland.model.business.Tag",
  "name": "abc",
  "fileCnt": NumberInt(1)
});
db.getCollection("tag").insert({
  "_id": ObjectId("5134ae092e71ac1b004281da"),
  "_class": "com.ireland.model.business.Tag",
  "name": "asdfdsa",
  "fileCnt": NumberInt(1)
});
db.getCollection("tag").insert({
  "_id": ObjectId("5134ae0b2e71ac1b004281db"),
  "_class": "com.ireland.model.business.Tag",
  "name": "adf",
  "fileCnt": NumberInt(1)
});
db.getCollection("tag").insert({
  "_id": ObjectId("5134ae102e71ac1b004281dc"),
  "_class": "com.ireland.model.business.Tag",
  "name": "asdfsf",
  "fileCnt": NumberInt(1)
});
db.getCollection("tag").insert({
  "_id": ObjectId("5134ae1f2e71ac1b004281dd"),
  "_class": "com.ireland.model.business.Tag",
  "name": "aasfasf",
  "fileCnt": NumberInt(1)
});
db.getCollection("tag").insert({
  "_id": ObjectId("5134af002e71761ac39329df"),
  "_class": "com.ireland.model.business.Tag",
  "name": "",
  "fileCnt": NumberInt(4)
});
db.getCollection("tag").insert({
  "_id": ObjectId("51518e086491206605a3e5b7"),
  "_class": "com.ireland.model.business.Tag",
  "name": "Good",
  "fileCnt": NumberInt(3)
});
db.getCollection("tag").insert({
  "_id": ObjectId("5151942c6491791f848fe878"),
  "_class": "com.ireland.model.business.Tag",
  "name": "sfsf",
  "fileCnt": NumberInt(1)
});
db.getCollection("tag").insert({
  "_id": ObjectId("515289856491688054bada23"),
  "_class": "com.ireland.model.business.Tag",
  "name": "video",
  "fileCnt": NumberInt(3)
});
db.getCollection("tag").insert({
  "_id": ObjectId("5155123a737f69addb977b5b"),
  "_class": "com.ireland.model.business.Tag",
  "name": "sfa",
  "fileCnt": NumberInt(1)
});
db.getCollection("tag").insert({
  "_id": ObjectId("51554150496587de110aa613"),
  "_class": "com.ireland.model.business.Tag",
  "name": "56565",
  "fileCnt": NumberInt(1)
});
db.getCollection("tag").insert({
  "_id": ObjectId("51554204496587de110aa616"),
  "_class": "com.ireland.model.business.Tag",
  "name": "sfdf",
  "fileCnt": NumberInt(1)
});
db.getCollection("tag").insert({
  "_id": ObjectId("515543d1496587de110aa619"),
  "_class": "com.ireland.model.business.Tag",
  "name": "4565",
  "fileCnt": NumberInt(1)
});
db.getCollection("tag").insert({
  "_id": ObjectId("5155595d496537d424d70680"),
  "_class": "com.ireland.model.business.Tag",
  "name": "Music",
  "fileCnt": NumberInt(2)
});
db.getCollection("tag").insert({
  "_id": ObjectId("519885408849e9ae2a3dff9d"),
  "_class": "com.ireland.model.business.Tag",
  "name": "music",
  "fileCnt": NumberInt(1)
});
db.getCollection("tag").insert({
  "_id": ObjectId("519885408849e9ae2a3dff9e"),
  "_class": "com.ireland.model.business.Tag",
  "name": "song",
  "fileCnt": NumberInt(1)
});
db.getCollection("tag").insert({
  "_id": ObjectId("51988ef58849e9ae2a3dffa1"),
  "_class": "com.ireland.model.business.Tag",
  "name": "Baby",
  "fileCnt": NumberInt(1)
});
db.getCollection("tag").insert({
  "_id": ObjectId("5198a05388499cc56de9adbc"),
  "_class": "com.ireland.model.business.Tag",
  "name": "some",
  "fileCnt": NumberInt(1)
});
db.getCollection("tag").insert({
  "_id": ObjectId("5198d8428849de74a5a5b2e5"),
  "_class": "com.ireland.model.business.Tag",
  "name": "undefined",
  "fileCnt": NumberInt(5)
});
db.getCollection("tag").insert({
  "_id": ObjectId("519a3f128849966bbc3961c4"),
  "_class": "com.ireland.model.business.Tag",
  "name": "Wanderful",
  "fileCnt": NumberInt(1)
});
db.getCollection("tag").insert({
  "_id": ObjectId("519a3f128849966bbc3961c5"),
  "_class": "com.ireland.model.business.Tag",
  "name": "mp3",
  "fileCnt": NumberInt(1)
});
db.getCollection("tag").insert({
  "_id": ObjectId("51a4a7d29737692ee418b8a2"),
  "_class": "com.ireland.model.business.Tag",
  "name": "netty",
  "fileCnt": NumberInt(1)
});
db.getCollection("tag").insert({
  "_id": ObjectId("51a4a7d29737692ee418b8a3"),
  "_class": "com.ireland.model.business.Tag",
  "name": "sever",
  "fileCnt": NumberInt(1)
});

/** terraFile records **/
db.getCollection("terraFile").insert({
  "_class": "com.ireland.model.business.TerraFile",
  "_id": ObjectId("512f110cc91bae835f85fe0b"),
  "comments": [
    
  ],
  "detail": "TMS SYS 2222",
  "downsCnt": NumberInt(0),
  "favsCnt": NumberInt(0),
  "fileOriginalName": "TMS-DMS.rar",
  "fileUrl": "\/staticfiles\/df8994c9-40be-4238-a128-4f7422859d0a-TMS-DMS.rar",
  "isShared": true,
  "name": "TMS889999",
  "owner": "admin2",
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("512f09e7c91b620384056328")
    }
  ],
  "type": "Bag",
  "uploadDate": ISODate("2013-02-28T08:10:16.214Z"),
  "viewsCnt": NumberInt(4)
});
db.getCollection("terraFile").insert({
  "_id": ObjectId("5155595d496537d424d70682"),
  "_class": "com.ireland.model.business.TerraFile",
  "name": "Music",
  "owner": "admin",
  "ownerId": "50dfaf31636b4356f5addf1d",
  "uploadDate": ISODate("2013-03-29T09:05:33.85Z"),
  "type": "Video",
  "detail": "Best Music",
  "isShared": false,
  "fileUrl": "\/staticfiles\/5465aa19-9e4a-46fe-9298-18892f05d518.mp3",
  "fileOriginalName": "0.mp3",
  "realFileId": "5155595d496537d424d70681",
  "viewsCnt": NumberInt(2),
  "favsCnt": NumberInt(0),
  "downsCnt": NumberInt(2),
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("5155595d496537d424d70680")
    }
  ],
  "comments": [
    
  ]
});

/** user records **/
db.getCollection("user").insert({
  "_class": "com.ireland.model.User",
  "_id": ObjectId("50dfb22c636b4356f5addf1e"),
  "password": "d13dd52e534fa20bb90d66fda54e26c35fdf1c60",
  "role": {
    "$ref": "role",
    "$id": ObjectId("50deec777259ea7d80c6fab3")
  },
  "trueName": "曹操",
  "username": "business"
});
db.getCollection("user").insert({
  "_class": "com.ireland.model.User",
  "_id": ObjectId("50dfaf31636b4356f5addf1d"),
  "password": "260acbffd3c30786febc29d7dd71a9880a811e77",
  "role": {
    "$ref": "role",
    "$id": ObjectId("50deec777259ea7d80c6fab3")
  },
  "trueName": "梁志峰",
  "username": "admin"
});
