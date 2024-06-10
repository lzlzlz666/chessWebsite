document.addEventListener("DOMContentLoaded", function() {
    const photos = [
        "../images/linzhou.png",
        "../images/lvzhenhua.jpg",
        "../images/luojiacheng.jpg",
        "../images/qiujiale.jpg",
        "../images/fanjinhao.jpg",
        "../images/ChatGPT.png"
    ];

    photos.forEach((photo, index) => {
        document.getElementById(`member-photo-${index + 1}`).style.backgroundImage = `url(${photo})`;
    });
});
