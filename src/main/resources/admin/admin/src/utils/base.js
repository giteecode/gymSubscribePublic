const base = {
    get() {
        return {
            url : "http://localhost:8080/tiyuguan/",
            name: "tiyuguan",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/tiyuguan/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "体育馆使用预约平台"
        } 
    }
}
export default base
