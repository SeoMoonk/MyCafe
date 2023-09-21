const editor = new toastui.Editor({
    el: document.querySelector('#editor'),
    height: '450px',
    initialEditType: 'markdown',
    previewStyle: 'vertical',
    theme: 'dark',
    toolbarItems: [
        [{
            name: 'bold',
            tooltip: 'bold',
            command: 'bold',
            text: 'Bold',
            className: 'toastui-editor-toolbar-icons',
            style: {backgroundImage: 'none', color: 'black'}
        },
            {
                name: 'heading',
                tooltip: 'heading',
                command: 'italic',
                text: 'Italic',
                className: 'toastui-editor-toolbar-icons',
                style: {backgroundImage: 'none', color: 'black'}
            },
            {
                name: 'strike',
                tooltip: 'strike',
                command: 'strike',
                text: '취소선',
                className: 'toastui-editor-toolbar-icons',
                style: {backgroundImage: 'none', color: 'black'}
            },
            {
                name: 'hr',
                tooltip: 'hr',
                command: 'hr',
                text: '구분선',
                className: 'toastui-editor-toolbar-icons',
                style: {backgroundImage: 'none', color: 'black'}
            }]
    ]
});

function writePost(e) {

    // DOM (document)
    const headerName = document.querySelector("meta[name='_csrf_header']").getAttribute("content");
    const tokenValue = document.querySelector("meta[name='_csrf']").getAttribute("content");

    // 제목
    const postTitle = document.querySelector("#postTitle").value;

    // 포스트의 내용
    const htmlBody = editor.getHTML();
    const markdownBody = editor.getMarkdown();

    //카페ID
    const cafeId = document.querySelector("#cafeId").value;

    const data = {
        "title": postTitle,
        htmlBody,
        markdownBody,
        cafeId
    };

    fetch("http://localhost:8080/post/write", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            [headerName]: tokenValue
        },
        body: JSON.stringify(data)
    })
        .then(
            (resp) => resp.json()
        )
        .then(
            (data) => {
                alert("포스트 작성이 완료되었습니다.");
                location.replace(`/post/detail/${data.postId}`);
            }
        )
        .catch(
            (err) => {
                console.log(err);
            }
        )
}
