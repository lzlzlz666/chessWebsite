function goToAnchor(conId) {
    // 使用replace方法可以保证前一次浏览不会在浏览器中保留记录
    window.location.replace(window.location.href.split('#')[0] + conId);
}


window.addEventListener('scroll', function () {
    const c1 = document.getElementById('c1');
    const c2 = document.getElementById('c2');
    const c3 = document.getElementById('c3');
    const c4 = document.getElementById('c4');
    const c5 = document.getElementById('c5');
    const c6 = document.getElementById('c6');
    const section1 = document.getElementById('con1');
    const section2 = document.getElementById('con2');
    const section3 = document.getElementById('con3');
    const section4 = document.getElementById('con4');
    const section5 = document.getElementById('con5');
    const section6 = document.getElementById('con6');
    const scrollPosition = window.scrollY;

    // 在滚动到 section1 时改变导航栏样式
    if (scrollPosition >= 0 && scrollPosition < section2.offsetTop - 10) {
        c1.classList.add('scrolled');
    } else {
        c1.classList.remove('scrolled');
    }
    if (scrollPosition >= section2.offsetTop - 10 && scrollPosition < section3.offsetTop - 10) {
        c2.classList.add('scrolled');
    } else {
        c2.classList.remove('scrolled');
    }
    if (scrollPosition >= section3.offsetTop - 10 && scrollPosition < section4.offsetTop - 10) {
        c3.classList.add('scrolled');
    } else {
        c3.classList.remove('scrolled');
    }
    if (scrollPosition >= section4.offsetTop - 10 && scrollPosition < section5.offsetTop - 10) {
        c4.classList.add('scrolled');
    } else {
        c4.classList.remove('scrolled');
    }
    if (scrollPosition >= section5.offsetTop - 10 && scrollPosition < section6.offsetTop - 10) {
        c5.classList.add('scrolled');
    } else {
        c5.classList.remove('scrolled');
    }
    if (scrollPosition >= section6.offsetTop - 10) {
        c6.classList.add('scrolled');
    } else {
        c6.classList.remove('scrolled');
    }
});