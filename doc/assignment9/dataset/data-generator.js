var fs = require("fs");
var data = fs.readFileSync("words.txt", "utf-8");
var titleWords = data.split("\n");
var authorWords = data.split("\n");
var users = new Array();
for (var i = 0; i < 100; i++) {
  users[i] = "User No." + i;
}
var commentUsers = users.concat([]);
var userMark = new Array();
var mark = new Array();
var curTime = (new Date()).getTime();

data = null;

function makeRecord(index) {
  var title = null;
  var author = null;
  var year = null;
  var conference = "ConferenceName No.";

  for (var i = 0; i < 5; i++) {
    var ran = random(0, titleWords.length);
    var da = titleWords[ran];
    if (title == null) {
      title = da;
    } else {
      if (title.search(da) != -1) {
        i--;
        continue;
      }
      title = title + " " + da;
    }

    if (mark[da] == 'undefined' || mark[da] == null) {
      mark[da] = 1
    } else {
      mark[da]++;
    }

    if (mark[da] == 250) {
      titleWords.splice(ran, 1);
    }
  }

  var authorLen = random(2, 2);
  for (var i = 0; i < authorLen; i++) {
    var ran = random(0, authorWords.length);
    var da = authorWords[ran];
    if (author == null) {
      author = da;
    } else {
      if (author.search(da) != -1) {
        i--;
        continue;
      }
      author = author + " " + da;
    }
  }

  year = random(1900, 115);
  conference = conference + random(1, 50);

  var publishDate = new Date(year, 0, 1);
  var publishTime = publishDate.getTime();
  var typeTime;
  var user;

  typeTime = random(publishTime, curTime - publishTime);

  var userPositon = random(0, users.length);
  var user = users[userPositon];
  if (userMark[userPositon] == 'undefined' || userMark[userPositon] == null) {
    userMark[userPositon] = 1
  } else {
    userMark[userPositon]++;
  }

  if (userMark[userPositon] == 1000) {
    users.splice(userPositon, 1);
  }

  var comments = null;
  var commentNum = random(2, 19);
  for (var i = 0; i < commentNum; i++) {
    var comment = null;
    var commentTime = random(typeTime, curTime - typeTime);
    var commentUser = commentUsers[random(0, 100)];
    comment = commentUser + "~" + getDateStringFromTime(commentTime);
    if (comments == null) {
      comments = comment;
    } else {
      comments = comments + "," + comment;
    }
  }

  return "Doc No." + index + "|" + year + "|" + conference + "|" + title + "|" + author + "|" + getDateStringFromTime(typeTime) + "|" + user + "|" + comments + "\n";
}

var log = fs.createWriteStream("data.txt", {
  flags: 'w'
});
console.time('make-time');
for (var i = 1; i <= 100000; i++) {
  log.write(makeRecord(i));
}
console.timeEnd('make-time');

function random(begin, length) {
  var va = Math.floor(Math.random() * length);
  return begin + va;
}

function getDateString(date) {
  return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
}

function getDateStringFromTime(time) {
  var date = new Date(time);
  return getDateString(date);
}
