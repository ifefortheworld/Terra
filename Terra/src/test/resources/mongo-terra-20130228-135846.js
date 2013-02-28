
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

/** track indexes **/
db.getCollection("track").ensureIndex({
  "_id": NumberInt(1)
},[
  
]);

/** track indexes **/
db.getCollection("track").ensureIndex({
  "licenseNumber": NumberInt(1)
},{
  "unique": true
});

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
  "votes": NumberInt(3)
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

/** role records **/
db.getCollection("role").insert({
  "_id": ObjectId("50def11f7259628f6d93888e"),
  "_class": "com.ireland.model.Role",
  "role": "ROLE_BUSINESS",
  "name": "业务",
  "authorities": [
    {
      "$ref": "authority",
      "$id": ObjectId("50deed7672594e11731e0b34")
    }
  ]
});
db.getCollection("role").insert({
  "_id": ObjectId("50deec777259ea7d80c6fab3"),
  "_class": "com.ireland.model.Role",
  "role": "ROLE_ADMIN",
  "name": "管理员",
  "authorities": [
    {
      "$ref": "authority",
      "$id": ObjectId("50deed7672594e11731e0b34")
    }
  ]
});
db.getCollection("role").insert({
  "_id": ObjectId("50def1897259b9d64facec90"),
  "_class": "com.ireland.model.Role",
  "role": "ROLE_SCHEDULE",
  "name": "调度",
  "authorities": [
    {
      "$ref": "authority",
      "$id": ObjectId("50deed7672594e11731e0b34")
    }
  ]
});
db.getCollection("role").insert({
  "_id": ObjectId("50def1d67259f8b22c7493b1"),
  "_class": "com.ireland.model.Role",
  "role": "ROLE_FINANCE",
  "name": "财务",
  "authorities": [
    {
      "$ref": "authority",
      "$id": ObjectId("50deed7672594e11731e0b34")
    }
  ]
});
db.getCollection("role").insert({
  "_id": ObjectId("50def20672599d3f0bbb9bee"),
  "_class": "com.ireland.model.Role",
  "role": "ROLE_ACCOUNTANT",
  "name": "会计",
  "authorities": [
    {
      "$ref": "authority",
      "$id": ObjectId("50deed7672594e11731e0b34")
    }
  ]
});
db.getCollection("role").insert({
  "_id": ObjectId("50def2217259ccb5db904a17"),
  "_class": "com.ireland.model.Role",
  "role": "ROLE_LEADER",
  "name": "领导",
  "authorities": [
    {
      "$ref": "authority",
      "$id": ObjectId("50deed7672594e11731e0b34")
    }
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
  "ns": "terra.track",
  "name": "_id_"
});
db.getCollection("system.indexes").insert({
  "v": NumberInt(1),
  "key": {
    "licenseNumber": NumberInt(1)
  },
  "unique": true,
  "ns": "terra.track",
  "name": "licenseNumber",
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
  "fileCnt": NumberInt(1)
});
db.getCollection("tag").insert({
  "_id": ObjectId("512c6edcc91b0b742f7ab9f4"),
  "_class": "com.ireland.model.business.Tag",
  "name": "Game",
  "fileCnt": NumberInt(1)
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
  "fileCnt": NumberInt(8)
});
db.getCollection("tag").insert({
  "_id": ObjectId("512f09e7c91b620384056328"),
  "_class": "com.ireland.model.business.Tag",
  "name": "TMS",
  "fileCnt": NumberInt(3)
});

/** terraFile records **/
db.getCollection("terraFile").insert({
  "_id": ObjectId("512c79c6c91b0f0670a8581c"),
  "_class": "com.ireland.model.business.TerraFile",
  "name": "The Last Day!",
  "owner": "admin",
  "uploadDate": ISODate("2013-02-26T09:00:52.701Z"),
  "type": "Video",
  "detail": "The Last Day! A Sad Movie!",
  "isShared": true,
  "viewsCnt": NumberInt(0),
  "favsCnt": NumberInt(0),
  "downsCnt": NumberInt(0),
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("512c690bc91b86bbe3ccd5fd")
    },
    {
      "$ref": "tag",
      "$id": ObjectId("512c6914c91b86bbe3ccd5fe")
    },
    {
      "$ref": "tag",
      "$id": ObjectId("512c6917c91b86bbe3ccd5ff")
    }
  ],
  "comments": [
    
  ]
});
db.getCollection("terraFile").insert({
  "_id": ObjectId("512c924ac91b85218640c811"),
  "_class": "com.ireland.model.business.TerraFile",
  "name": "Me",
  "owner": "admin",
  "uploadDate": ISODate("2013-02-26T10:45:30.68Z"),
  "type": "Image",
  "detail": "A handsome man!",
  "isShared": false,
  "viewsCnt": NumberInt(1),
  "favsCnt": NumberInt(0),
  "downsCnt": NumberInt(0),
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("512c924ac91b85218640c80f")
    },
    {
      "$ref": "tag",
      "$id": ObjectId("512c924ac91b85218640c810")
    }
  ],
  "comments": [
    
  ]
});
db.getCollection("terraFile").insert({
  "_id": ObjectId("512cbaf5c91b9624dd17ba58"),
  "_class": "com.ireland.model.business.TerraFile",
  "name": "On The Floor!",
  "owner": "admin",
  "uploadDate": ISODate("2013-02-26T13:39:00.223Z"),
  "type": "Audio",
  "detail": "Jennifer Lopez(詹妮弗·洛佩兹)，父母是拉丁美洲的波多黎各人，她出生于美国的Bronx city，身兼演员、歌手、电视制作人、流行设计师与舞者等多职。她是好莱坞最富有的西班牙语系人物。根据由西班牙裔投票的“100个最具影响力的西班牙语系人物”排行榜，她成为了美国最具影响力的西班牙语系艺人。",
  "isShared": true,
  "viewsCnt": NumberInt(2),
  "favsCnt": NumberInt(0),
  "downsCnt": NumberInt(0),
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("512cbaefc91b9624dd17ba56")
    },
    {
      "$ref": "tag",
      "$id": ObjectId("512cbaf1c91b9624dd17ba57")
    }
  ],
  "comments": [
    
  ]
});
db.getCollection("terraFile").insert({
  "_id": ObjectId("512ccafec91bebd24ec9375e"),
  "_class": "com.ireland.model.business.TerraFile",
  "name": "One Day",
  "owner": "admin",
  "uploadDate": ISODate("2013-02-26T14:47:25.370Z"),
  "type": "Video",
  "detail": "“这么多年，这么多人经过我的生活，可是为什么偏偏是你，看起来好像最应该是过客的你，在我心中占据了这么重的地位……”男生与女生之间，是否真的能有一段跨越20年的友谊？为什么每一年，只为等待这一天？最新上映的好莱坞新片《One Day》中，安妮·海瑟薇用她小心翼翼的约克郡口音，讲述了一段“年年有今日”的无悔约定。",
  "isShared": false,
  "viewsCnt": NumberInt(0),
  "favsCnt": NumberInt(0),
  "downsCnt": NumberInt(0),
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("512ccaf8c91bebd24ec9375d")
    },
    {
      "$ref": "tag",
      "$id": ObjectId("512c6917c91b86bbe3ccd5ff")
    }
  ],
  "comments": [
    
  ]
});
db.getCollection("terraFile").insert({
  "_id": ObjectId("512d5fb6c91b082db7272d0f"),
  "_class": "com.ireland.model.business.TerraFile",
  "name": "Love Again",
  "owner": "admin",
  "uploadDate": ISODate("2013-02-27T01:21:58.72Z"),
  "type": "Bag",
  "detail": "在他知道了他歌唱的风格之后，Lukie D开始了他的职业生涯在不同的地方作音乐表演，建立一个当舞厅歌手的良好声誉。所影响的包括当地英雄Frankie Paul、Tenor Saw以及ragga DJ Supercat。Lukie D的声望使录音产生了很大的冲击，后来於Centre Of Attraction与Lieutenant Stitchie合作<Don't Deny Me>， 亦跟Beenie Man及Black Pearl合作过<Bag It Up>。Lukie?加入了Firehouse Crew后发表了他一系列的歌曲，包括：<I Won't Let You Go>、<Lonely Nights>、<Let Me Love You Now>、<Heavy Load>、<You've Got It Going>等。他的成功使他开始往返美国和牙买加，而他对音乐的熟悉，使他的演奏曲目愈来愈多样化。1997年他发表了20首充满特色，突破传统的歌曲。AC\/DC 's 版本的<You Shook Me>，与Queen 's的<We Will Rock You>。虽然疏远了党派舞厅，不过，Lukie D却在美国获得了关注，单飞后在纽约的Culturama festival上也获得了一致好评。",
  "isShared": true,
  "viewsCnt": NumberInt(0),
  "favsCnt": NumberInt(0),
  "downsCnt": NumberInt(0),
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("512d5fb6c91b082db7272d0d")
    },
    {
      "$ref": "tag",
      "$id": ObjectId("512d5fb6c91b082db7272d0e")
    }
  ],
  "comments": [
    
  ]
});
db.getCollection("terraFile").insert({
  "_id": ObjectId("512d6084c91b082db7272d10"),
  "_class": "com.ireland.model.business.TerraFile",
  "name": "Love Again",
  "owner": "admin",
  "uploadDate": ISODate("2013-02-27T01:25:24.344Z"),
  "type": "Text",
  "detail": "在他知道了他歌唱的风格之后，Lukie D开始了他的职业生涯在不同的地方作音乐表演，建立一个当舞厅歌手的良好声誉。所影响的包括当地英雄Frankie Paul、Tenor Saw以及ragga DJ Supercat。Lukie D的声望使录音产生了很大的冲击，后来於Centre Of Attraction与Lieutenant Stitchie合作<Don't Deny Me>， 亦跟Beenie Man及Black Pearl合作过<Bag It Up>。Lukie?加入了Firehouse Crew后发表了他一系列的歌曲，包括：<I Won't Let You Go>、<Lonely Nights>、<Let Me Love You Now>、<Heavy Load>、<You've Got It Going>等。他的成功使他开始往返美国和牙买加，而他对音乐的熟悉，使他的演奏曲目愈来愈多样化。1997年他发表了20首充满特色，突破传统的歌曲。AC\/DC 's 版本的<You Shook Me>，与Queen 's的<We Will Rock You>。虽然疏远了党派舞厅，不过，Lukie D却在美国获得了关注，单飞后在纽约的Culturama festival上也获得了一致好评。",
  "isShared": false,
  "viewsCnt": NumberInt(0),
  "favsCnt": NumberInt(0),
  "downsCnt": NumberInt(0),
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("512d5fb6c91b082db7272d0d")
    },
    {
      "$ref": "tag",
      "$id": ObjectId("512d5fb6c91b082db7272d0e")
    }
  ],
  "comments": [
    
  ]
});
db.getCollection("terraFile").insert({
  "_id": ObjectId("512d61a4c91b082db7272d11"),
  "_class": "com.ireland.model.business.TerraFile",
  "name": "Love Again",
  "owner": "admin",
  "uploadDate": ISODate("2013-02-27T01:30:12.552Z"),
  "type": "Audio",
  "detail": "在他知道了他歌唱的风格之后，Lukie D开始了他的职业生涯在不同的地方作音乐表演，建立一个当舞厅歌手的良好声誉。所影响的包括当地英雄Frankie Paul、Tenor Saw以及ragga DJ Supercat。Lukie D的声望使录音产生了很大的冲击，后来於Centre Of Attraction与Lieutenant Stitchie合作<Don't Deny Me>， 亦跟Beenie Man及Black Pearl合作过<Bag It Up>。Lukie?加入了Firehouse Crew后发表了他一系列的歌曲，包括：<I Won't Let You Go>、<Lonely Nights>、<Let Me Love You Now>、<Heavy Load>、<You've Got It Going>等。他的成功使他开始往返美国和牙买加，而他对音乐的熟悉，使他的演奏曲目愈来愈多样化。1997年他发表了20首充满特色，突破传统的歌曲。AC\/DC 's 版本的<You Shook Me>，与Queen 's的<We Will Rock You>。虽然疏远了党派舞厅，不过，Lukie D却在美国获得了关注，单飞后在纽约的Culturama festival上也获得了一致好评。",
  "isShared": false,
  "viewsCnt": NumberInt(0),
  "favsCnt": NumberInt(0),
  "downsCnt": NumberInt(0),
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("512d5fb6c91b082db7272d0d")
    },
    {
      "$ref": "tag",
      "$id": ObjectId("512d5fb6c91b082db7272d0e")
    }
  ],
  "comments": [
    
  ]
});
db.getCollection("terraFile").insert({
  "_id": ObjectId("512d620fc91b082db7272d12"),
  "_class": "com.ireland.model.business.TerraFile",
  "name": "Love Again",
  "owner": "admin",
  "uploadDate": ISODate("2013-02-27T01:30:59.906Z"),
  "type": "Image",
  "detail": "在他知道了他歌唱的风格之后，Lukie D开始了他的职业生涯在不同的地方作音乐表演，建立一个当舞厅歌手的良好声誉。所影响的包括当地英雄Frankie Paul、Tenor Saw以及ragga DJ Supercat。Lukie D的声望使录音产生了很大的冲击，后来於Centre Of Attraction与Lieutenant Stitchie合作<Don't Deny Me>， 亦跟Beenie Man及Black Pearl合作过<Bag It Up>。Lukie?加入了Firehouse Crew后发表了他一系列的歌曲，包括：<I Won't Let You Go>、<Lonely Nights>、<Let Me Love You Now>、<Heavy Load>、<You've Got It Going>等。他的成功使他开始往返美国和牙买加，而他对音乐的熟悉，使他的演奏曲目愈来愈多样化。1997年他发表了20首充满特色，突破传统的歌曲。AC\/DC 's 版本的<You Shook Me>，与Queen 's的<We Will Rock You>。虽然疏远了党派舞厅，不过，Lukie D却在美国获得了关注，单飞后在纽约的Culturama festival上也获得了一致好评。",
  "isShared": false,
  "viewsCnt": NumberInt(0),
  "favsCnt": NumberInt(0),
  "downsCnt": NumberInt(0),
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("512d5fb6c91b082db7272d0d")
    },
    {
      "$ref": "tag",
      "$id": ObjectId("512d5fb6c91b082db7272d0e")
    }
  ],
  "comments": [
    
  ]
});
db.getCollection("terraFile").insert({
  "_id": ObjectId("512d65ccc91b082db7272d13"),
  "_class": "com.ireland.model.business.TerraFile",
  "name": "Love Again",
  "owner": "admin",
  "uploadDate": ISODate("2013-02-27T01:32:25.1Z"),
  "type": "Video",
  "detail": "在他知道了他歌唱的风格之后，Lukie D开始了他的职业生涯在不同的地方作音乐表演，建立一个当舞厅歌手的良好声誉。所影响的包括当地英雄Frankie Paul、Tenor Saw以及ragga DJ Supercat。Lukie D的声望使录音产生了很大的冲击，后来於Centre Of Attraction与Lieutenant Stitchie合作<Don't Deny Me>， 亦跟Beenie Man及Black Pearl合作过<Bag It Up>。Lukie?加入了Firehouse Crew后发表了他一系列的歌曲，包括：<I Won't Let You Go>、<Lonely Nights>、<Let Me Love You Now>、<Heavy Load>、<You've Got It Going>等。他的成功使他开始往返美国和牙买加，而他对音乐的熟悉，使他的演奏曲目愈来愈多样化。1997年他发表了20首充满特色，突破传统的歌曲。AC\/DC 's 版本的<You Shook Me>，与Queen 's的<We Will Rock You>。虽然疏远了党派舞厅，不过，Lukie D却在美国获得了关注，单飞后在纽约的Culturama festival上也获得了一致好评。",
  "isShared": true,
  "viewsCnt": NumberInt(0),
  "favsCnt": NumberInt(0),
  "downsCnt": NumberInt(0),
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("512d5fb6c91b082db7272d0d")
    },
    {
      "$ref": "tag",
      "$id": ObjectId("512d5fb6c91b082db7272d0e")
    }
  ],
  "comments": [
    
  ]
});
db.getCollection("terraFile").insert({
  "_id": ObjectId("512d660cc91b082db7272d14"),
  "_class": "com.ireland.model.business.TerraFile",
  "name": "Love Again",
  "owner": "admin",
  "uploadDate": ISODate("2013-02-27T01:49:00.336Z"),
  "type": "Other",
  "detail": "在他知道了他歌唱的风格之后，Lukie D开始了他的职业生涯在不同的地方作音乐表演，建立一个当舞厅歌手的良好声誉。所影响的包括当地英雄Frankie Paul、Tenor Saw以及ragga DJ Supercat。Lukie D的声望使录音产生了很大的冲击，后来於Centre Of Attraction与Lieutenant Stitchie合作<Don't Deny Me>， 亦跟Beenie Man及Black Pearl合作过<Bag It Up>。Lukie?加入了Firehouse Crew后发表了他一系列的歌曲，包括：<I Won't Let You Go>、<Lonely Nights>、<Let Me Love You Now>、<Heavy Load>、<You've Got It Going>等。他的成功使他开始往返美国和牙买加，而他对音乐的熟悉，使他的演奏曲目愈来愈多样化。1997年他发表了20首充满特色，突破传统的歌曲。AC\/DC 's 版本的<You Shook Me>，与Queen 's的<We Will Rock You>。虽然疏远了党派舞厅，不过，Lukie D却在美国获得了关注，单飞后在纽约的Culturama festival上也获得了一致好评。",
  "isShared": true,
  "viewsCnt": NumberInt(0),
  "favsCnt": NumberInt(0),
  "downsCnt": NumberInt(0),
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("512d5fb6c91b082db7272d0d")
    },
    {
      "$ref": "tag",
      "$id": ObjectId("512d5fb6c91b082db7272d0e")
    }
  ],
  "comments": [
    
  ]
});
db.getCollection("terraFile").insert({
  "_id": ObjectId("512d6614c91b082db7272d15"),
  "_class": "com.ireland.model.business.TerraFile",
  "name": "Love Again",
  "owner": "admin",
  "uploadDate": ISODate("2013-02-27T01:49:08.707Z"),
  "type": "Text",
  "detail": "在他知道了他歌唱的风格之后，Lukie D开始了他的职业生涯在不同的地方作音乐表演，建立一个当舞厅歌手的良好声誉。所影响的包括当地英雄Frankie Paul、Tenor Saw以及ragga DJ Supercat。Lukie D的声望使录音产生了很大的冲击，后来於Centre Of Attraction与Lieutenant Stitchie合作<Don't Deny Me>， 亦跟Beenie Man及Black Pearl合作过<Bag It Up>。Lukie?加入了Firehouse Crew后发表了他一系列的歌曲，包括：<I Won't Let You Go>、<Lonely Nights>、<Let Me Love You Now>、<Heavy Load>、<You've Got It Going>等。他的成功使他开始往返美国和牙买加，而他对音乐的熟悉，使他的演奏曲目愈来愈多样化。1997年他发表了20首充满特色，突破传统的歌曲。AC\/DC 's 版本的<You Shook Me>，与Queen 's的<We Will Rock You>。虽然疏远了党派舞厅，不过，Lukie D却在美国获得了关注，单飞后在纽约的Culturama festival上也获得了一致好评。",
  "isShared": true,
  "viewsCnt": NumberInt(0),
  "favsCnt": NumberInt(0),
  "downsCnt": NumberInt(0),
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("512d5fb6c91b082db7272d0d")
    },
    {
      "$ref": "tag",
      "$id": ObjectId("512d5fb6c91b082db7272d0e")
    }
  ],
  "comments": [
    
  ]
});
db.getCollection("terraFile").insert({
  "_id": ObjectId("512db90bc91b4daa738ce8f3"),
  "_class": "com.ireland.model.business.TerraFile",
  "name": "I Love U",
  "owner": "admin",
  "uploadDate": ISODate("2013-02-27T07:43:07.536Z"),
  "type": "Text",
  "detail": "你和我慢慢地走着 手拉手 在沙滩上的脚印 看风 在你的脸上投掷阴影 天空是那么蓝 你的眼睛如此翠绿 空气是闪闪发光 那么突然的如此之快 爱来找我",
  "isShared": false,
  "viewsCnt": NumberInt(0),
  "favsCnt": NumberInt(0),
  "downsCnt": NumberInt(0),
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("512d6ebbc91bf435ecb88bc8")
    }
  ],
  "comments": [
    
  ]
});
db.getCollection("terraFile").insert({
  "_id": ObjectId("512db91fc91b4daa738ce8f4"),
  "_class": "com.ireland.model.business.TerraFile",
  "name": "I Love U",
  "owner": "admin",
  "uploadDate": ISODate("2013-02-27T07:43:27.950Z"),
  "type": "Bag",
  "detail": "你和我慢慢地走着 手拉手 在沙滩上的脚印 看风 在你的脸上投掷阴影 天空是那么蓝 你的眼睛如此翠绿 空气是闪闪发光 那么突然的如此之快 爱来找我",
  "isShared": false,
  "viewsCnt": NumberInt(0),
  "favsCnt": NumberInt(0),
  "downsCnt": NumberInt(0),
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("512d6ebbc91bf435ecb88bc8")
    }
  ],
  "comments": [
    
  ]
});
db.getCollection("terraFile").insert({
  "_id": ObjectId("512db924c91b4daa738ce8f5"),
  "_class": "com.ireland.model.business.TerraFile",
  "name": "I Love U",
  "owner": "admin",
  "uploadDate": ISODate("2013-02-27T07:43:32.387Z"),
  "type": "Other",
  "detail": "你和我慢慢地走着 手拉手 在沙滩上的脚印 看风 在你的脸上投掷阴影 天空是那么蓝 你的眼睛如此翠绿 空气是闪闪发光 那么突然的如此之快 爱来找我",
  "isShared": false,
  "viewsCnt": NumberInt(0),
  "favsCnt": NumberInt(0),
  "downsCnt": NumberInt(0),
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("512d6ebbc91bf435ecb88bc8")
    }
  ],
  "comments": [
    
  ]
});
db.getCollection("terraFile").insert({
  "_id": ObjectId("512db92bc91b4daa738ce8f6"),
  "_class": "com.ireland.model.business.TerraFile",
  "name": "I Love U",
  "owner": "admin",
  "uploadDate": ISODate("2013-02-27T07:43:39.355Z"),
  "type": "Video",
  "detail": "你和我慢慢地走着 手拉手 在沙滩上的脚印 看风 在你的脸上投掷阴影 天空是那么蓝 你的眼睛如此翠绿 空气是闪闪发光 那么突然的如此之快 爱来找我",
  "isShared": false,
  "viewsCnt": NumberInt(0),
  "favsCnt": NumberInt(0),
  "downsCnt": NumberInt(0),
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("512d6ebbc91bf435ecb88bc8")
    }
  ],
  "comments": [
    
  ]
});
db.getCollection("terraFile").insert({
  "_id": ObjectId("512db930c91b4daa738ce8f7"),
  "_class": "com.ireland.model.business.TerraFile",
  "name": "I Love U",
  "owner": "admin",
  "uploadDate": ISODate("2013-02-27T07:43:44.382Z"),
  "type": "Bag",
  "detail": "你和我慢慢地走着 手拉手 在沙滩上的脚印 看风 在你的脸上投掷阴影 天空是那么蓝 你的眼睛如此翠绿 空气是闪闪发光 那么突然的如此之快 爱来找我",
  "isShared": false,
  "viewsCnt": NumberInt(0),
  "favsCnt": NumberInt(0),
  "downsCnt": NumberInt(0),
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("512d6ebbc91bf435ecb88bc8")
    }
  ],
  "comments": [
    
  ]
});
db.getCollection("terraFile").insert({
  "_id": ObjectId("512db93fc91b4daa738ce8f8"),
  "_class": "com.ireland.model.business.TerraFile",
  "name": "I Love U",
  "owner": "admin",
  "uploadDate": ISODate("2013-02-27T07:43:59.993Z"),
  "type": "Bag",
  "detail": "你和我慢慢地走着 手拉手 在沙滩上的脚印 看风 在你的脸上投掷阴影 天空是那么蓝 你的眼睛如此翠绿 空气是闪闪发光 那么突然的如此之快 爱来找我",
  "isShared": false,
  "viewsCnt": NumberInt(0),
  "favsCnt": NumberInt(0),
  "downsCnt": NumberInt(0),
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("512d6ebbc91bf435ecb88bc8")
    }
  ],
  "comments": [
    
  ]
});
db.getCollection("terraFile").insert({
  "_id": ObjectId("512db945c91b4daa738ce8f9"),
  "_class": "com.ireland.model.business.TerraFile",
  "name": "I Love U",
  "owner": "admin",
  "uploadDate": ISODate("2013-02-27T07:44:05.201Z"),
  "type": "Video",
  "detail": "你和我慢慢地走着 手拉手 在沙滩上的脚印 看风 在你的脸上投掷阴影 天空是那么蓝 你的眼睛如此翠绿 空气是闪闪发光 那么突然的如此之快 爱来找我",
  "isShared": false,
  "viewsCnt": NumberInt(0),
  "favsCnt": NumberInt(0),
  "downsCnt": NumberInt(0),
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("512d6ebbc91bf435ecb88bc8")
    }
  ],
  "comments": [
    
  ]
});
db.getCollection("terraFile").insert({
  "_id": ObjectId("512f0a05c91b620384056329"),
  "_class": "com.ireland.model.business.TerraFile",
  "name": "TMS 123",
  "owner": "admin",
  "uploadDate": ISODate("2013-02-28T07:40:25.141Z"),
  "type": "Other",
  "detail": "TMS SYS",
  "isShared": true,
  "viewsCnt": NumberInt(0),
  "favsCnt": NumberInt(0),
  "downsCnt": NumberInt(0),
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("512f09e7c91b620384056328")
    }
  ],
  "comments": [
    
  ]
});
db.getCollection("terraFile").insert({
  "_id": ObjectId("512f1075c91bae835f85fe0a"),
  "_class": "com.ireland.model.business.TerraFile",
  "name": "TMS88888",
  "owner": "admin",
  "uploadDate": ISODate("2013-02-28T08:08:21.213Z"),
  "type": "Bag",
  "detail": "TMS SYS 2222",
  "isShared": false,
  "fileUrl": "\/staticfiles\/9a908a14-01d7-4046-b2af-263791a1ebb4-TMS-DMS.rar",
  "fileOriginalName": "TMS-DMS.rar",
  "viewsCnt": NumberInt(19),
  "favsCnt": NumberInt(0),
  "downsCnt": NumberInt(9),
  "tags": [
    {
      "$ref": "tag",
      "$id": ObjectId("512f09e7c91b620384056328")
    }
  ],
  "comments": [
    
  ]
});
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

/** track records **/

/** user records **/
db.getCollection("user").insert({
  "_id": ObjectId("50dfb22c636b4356f5addf1e"),
  "_class": "com.ireland.model.User",
  "username": "business",
  "password": "d13dd52e534fa20bb90d66fda54e26c35fdf1c60",
  "trueName": "曹操",
  "role": {
    "$ref": "role",
    "$id": ObjectId("50def11f7259628f6d93888e")
  }
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
