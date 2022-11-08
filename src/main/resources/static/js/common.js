function handleClickOutside(event) {
  const obj = document.getElementsByTagName("nav")[0];

  if (!obj.contains(event.target)) {
    btnClose();
  }
}

function btnClose() {
  $("body").css("overflow", "");
  $("nav").animate({ right: "-100%" }, 250, function () {
    $("nav").hide();
    $(".overlay").hide();
  });
  document.removeEventListener("mousedown", handleClickOutside);
}

function btnMenu() {
  $("body").css("overflow", "hidden");
  $(".overlay").show();
  $("nav").show().animate({ right: "0" }, 250);

  document.addEventListener("mousedown", handleClickOutside);
}

function ScrollToTop() {
  $("html, body").animate({ scrollTop: 0 }, "normal");
  return false;
}
function addClassName(tagName, className) {
  $(tagName).addClass(className);
}

function tab(event, tabName) {
  const tabcontent = document.getElementsByClassName("tab-content");
  for (let i = 0; i < tabcontent.length; i++)
    tabcontent[i].classList.remove("active");

  const tablinks = document.getElementsByClassName("tab-links");
  for (let i = 0; i < tablinks.length; i++)
    tablinks[i].classList.remove("active");

  document.getElementById(tabName).classList.add("active");
  event.currentTarget.classList.add("active");
}

(function ($, window) {
  function createElement() {
    // $(document).ready(function () {
    //   $("#header").load("/html/layout/header.html");
    // });
    var allElements = document.getElementsByTagName("*");
    Array.prototype.forEach.call(allElements, function (el) {
      var includePath = el.dataset.includePath;
      if (includePath) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
          if (this.readyState == 4 && this.status == 200) {
            el.outerHTML = this.responseText;
          }
          // class 추가하기
          if (el.classList)
            xhttp.addEventListener("loadend", () => {
              el.classList.forEach((className) => {
                $(el.tagName).addClass(className);
              });
            });
        };
        xhttp.open("GET", includePath, true);
        xhttp.send();
      }
    });
  }

  function goBack() {
    $(".pbtn-back").click(function () {
      history.back();
    });
  }

  function goHome() {
    $("header .logo").click(function () {
      // server
      // location.href = "/";

      // local
      if (location.pathname.includes("index.html"))
        location.href = "./index.html";
      else location.href = "../index.html";
      return false;
    });
  }

  $(window).on({
    load: function () {
      createElement();
      goBack();
      goHome();
    },
  });
})(jQuery, window);
