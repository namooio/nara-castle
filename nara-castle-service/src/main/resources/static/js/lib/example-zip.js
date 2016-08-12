/**
 * Created by jiyong on 2016. 8. 12..
 */
zipDownloadOnClick() {
    // start signal
    this.setState({btnText: '압축중..'});

    // prepare data
    let param = {
        naraFileName: '압축파일',
        naraFilePaths: [
            {fileId: '0002', filePath: '/'},
            {fileId: '0006', filePath: ''},
            {fileId: '0008', filePath: '/오늘하루'},
            {fileId: '001B', filePath: '/오늘하루'},
            {fileId: '0002', filePath: '/오늘'},
            {fileId: '001B', filePath: '/오늘하루/fff'}
        ]
    };

    // success function (1st argument : download url)
    let successCallback = function (url) {
        //
        alert("압축파일 생성 완료! 다운로드 url : " + url);
        window.location = url;
        this.setState({btnText: '완료!'});
    }.bind(this);

    // fail function (no argument)
    let failCallback = function () {
        //
        alert("압축파일 생성 실패!");
        this.setState({btnText: '실패!'});
    }.bind(this);

    // request
    NaraFile.ZipFileloader.requestZipFile(param, successCallback, failCallback);
}