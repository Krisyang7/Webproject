function showContent(targetId) {
    // 隐藏所有的center_bar
    var centerBars = document.getElementsByClassName('center_bar');
    for (var i = 0; i < centerBars.length; i++) {
        centerBars[i].style.display = 'none';
    }

    // 显示指定的center_bar
    var target = document.getElementById(targetId);
    if (target) {
        target.style.display = 'block';
    }
}