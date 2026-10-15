# SmartTite

Ứng dụng Java console mô phỏng hệ thống quản lý công trường xây dựng, làm cho môn PRO192. Chương trình chạy hoàn toàn trên terminal: hiển thị banner, cho người dùng đăng nhập bằng mã nhân sự, rồi mở menu theo vai trò (công nhân, nhà thầu, khách tham quan, cán bộ an toàn, quản lý công trường). Dữ liệu mẫu được để trong các file `.txt` ở thư mục gốc.

Repo hiện là một dự án NetBeans kiểu Java SE với Ant (`build.xml` + `nbproject/`), tên project là `SmartTite`.

Repository: <https://github.com/minhkhang-IO/SMARTSITE_AI_INTEGRATED_CONSTRUCTION_SITE_MANAGEMENT_SYSTEM>

## Trong repo có gì

| Đường dẫn | Nội dung |
| --- | --- |
| `src/smarttite/` | Package nghiệp vụ: các lớp mô hình dữ liệu (`Person`, `Worker`, `Visitor`, `Zone`, `Incident`, `AttendanceRecord`) và các lớp quản lý danh sách (`PersonManager`, `ZoneManager`, `IncidentManager`) |
| `src/ui/` | Package giao diện terminal: `WelcomeMenu`, `LoginMenu`, `MenuUserLv01` đến `MenuUserLv03`, và vài lớp menu còn để trống |
| `Person.txt`, `Zone.txt`, `Incident.txt` | Dữ liệu mẫu cho người, khu vực, sự cố |
| `build.xml`, `nbproject/project.properties`, `nbproject/project.xml` | Cấu hình dự án NetBeans (nguồn `src`, main class `ui.TempMainToTestFeatures`, mức ngôn ngữ Java 8) |
| `manifest.mf` | Manifest cho file jar, phần `Main-Class` do build sinh ra |
| `build/classes/` | File `.class` được commit vào repo (thư mục build của NetBeans) |
| `SmartTite/` | Bản sao cũ của project, hiện chưa được git theo dõi (xem phần ghi chú bên dưới) |

Hai file khác đang nằm ngoài git: `.gitignore` và `LAB2-TranMinhKhang-SE203117.rar`.

## Yêu cầu môi trường

- JDK 8 trở lên. `nbproject/project.properties` đặt `javac.source=1.8` và `javac.target=1.8`; máy dùng để kiểm tra phần README này chạy `javac 1.8.0_202`.
- NetBeans nếu muốn build bằng IDE (NetBeans đã kèm Ant sẵn). Nếu chỉ dùng dòng lệnh thì cần `javac` và `java`, không cần cài Ant.
## Build và chạy

### Bằng NetBeans

1. `File > Open Project` và chọn thư mục gốc của repo (thư mục có `build.xml`).
2. Bấm `Run Project` (F6). NetBeans sẽ chạy class `ui.TempMainToTestFeatures`, đúng như khai báo `main.class` trong `nbproject/project.properties`.

### Bằng dòng lệnh

```bat
javac -encoding UTF-8 -d build_out src\smarttite\*.java src\ui\*.java
java -cp build_out ui.TempMainToTestFeatures
```

Lệnh trên đúng với cấu trúc package hiện tại nhưng **đang chưa chạy được với bản làm việc hiện tại**, vì `src/smarttite/PersonManager.java` có một ký tự `1` thừa ngay sau dấu `}` đóng phương thức `displayAll()`:

```java
    ...
}
}1
}
```

`javac` báo:

```
src\smarttite\PersonManager.java:93: error: illegal start of type
}1
 ^
3 errors
```

Xóa ký tự `1` đó là biên dịch lại được. Đây là sửa đổi chưa commit trong thư mục làm việc; hai file `src/smarttite/Main.java` và `src/smarttite/PersonManager.java` hiện khác bản đã commit (chi tiết ở mục Trạng thái hiện tại).

Muốn chạy thử phần nghiệp vụ không cần menu, có thể chạy `smarttite.Main`:

```bat
java -cp build_out smarttite.Main
```

Nhưng bản `Main` trong thư mục làm việc đang gọi `new PersonManager(5)` trong khi constructor nhận tham số `int` chỉ ném `UnsupportedOperationException`, nên sẽ lỗi lúc chạy cho tới khi hai chỗ này khớp nhau.

### Một số điểm cần lưu ý khi dùng

- `MenuUserLv03.displayMenuLv03()` đọc lựa chọn bằng `Scanner.nextInt()`, nên nhập số rồi Enter, không nhập kèm chữ.
- `WelcomeMenu` không bắt lỗi nhập liệu: gõ chữ vào ô chọn `[1-3]` sẽ làm `Integer.parseInt` ném `NumberFormatException`.
- `WelcomeMenu.clearConsoleIDE()` chỉ in ra 50 dòng trống để "làm mới" màn hình, không xóa thật nội dung terminal.

## Luồng màn hình

`ui.TempMainToTestFeatures` là điểm vào hiện tại, nó gọi `WelcomeMenu.displayWelcomeMenu()`. Từ đó:

1. `WelcomeMenu` in banner ASCII, giờ hệ thống, rồi ba lựa chọn: `1. Sign in`, `2. View area information`, `3. Exit`. Chọn 1 thì mở `LoginMenu`, chọn 2 hiện mới chỉ có lời gọi `displayZoneInformation()` bị comment, chọn 3 thì thoát vòng lặp.
2. `LoginMenu` hỏi tên đăng nhập và mật khẩu, in thẳng cả hai ra màn hình (dòng `User name: ...; password: ...`), rồi nhảy vào menu người dùng. Bước lấy quyền theo tài khoản còn bị comment: biến `role` đang được gán cứng là `"Visitor"`, và `switch (role)` thiếu `break` ở mọi nhánh nên tất cả nhánh đều chạy xuống dưới.
3. `MenuUserLv03` in menu chung: xem thông tin cá nhân, check-in, check-out, lịch sử điểm danh, đăng xuất (`0`). Nội dung của từng mục đang bị comment hết, chỉ có khung và phần bắt lựa chọn.
4. `MenuUserLv02` kế thừa `MenuUserLv03` và thêm mục `5. Take tools`, `MenuUserLv01` kế thừa `MenuUserLv02` và thêm mục `6. Show History Attendance All`. Hai lớp này chỉ gọi lại phương thức của lớp cha rồi in thêm một dòng.

Các lớp `displayMenu`, `UserMenu01_SafetyOfficers_SiteManagers`, `UserMenu02_Worker_Contractors`, `UserMenu03_Visitors` hiện là file rỗng, chưa có phương thức nào.

## Package `smarttite`

### Mô hình dữ liệu

| Lớp | Thuộc tính | Ghi chú |
| --- | --- | --- |
| `Person` | `id`, `fullName`, `code`, `role`, `status` | `setid` và `setFullName` ném `IllegalArgumentException` khi giá trị rỗng. `isActive()` so `status` với `"ACTIVE"` không phân biệt hoa thường. `toFileLine()` trả về `role|id|fullName|code|status` |
| `Worker extends Person` | `companyName`, `contractType` | Có constructor 7 tham số và constructor 5 tham số (đổ chuỗi rỗng cho hai trường riêng) |
| `Visitor extends Person` | `visitPurpose`, `hostName` | Ghi đè `toFileLine()` để thêm hai trường riêng vào cuối dòng |
| `Zone` | `zoneId`, `zoneName`, `zoneDetails` | Hai setter đầu kiểm tra dữ liệu rỗng. Lớp chưa có `toString()` nên in ra chỉ thấy địa chỉ đối tượng |
| `Incident` | `incidentId`, `zoneId`, `reportId`, `assigneeID`, `description`, `incidentStatus`, `locateDateTime` | `locateDateTime` khai báo là `String` dù file có import `java.time.LocalDateTime`. Hai getter bị gõ sai tên: `getAssigNeeld()`, `getDesCripTion()` |
| `AttendanceRecord` | `recordId`, `personId`, `zoneId`, `checkInTime`, `checkOutTime` | Ba trường đầu là `final`. `isOpen()`, `markCheckOut()`, `calculateDurationMinutes()` (đang mở thì tính tới thời điểm gọi), `toFileLine()` nối bằng dấu `|` |

### Các lớp quản lý

`PersonManager` giữ một mảng `Person[]` cố định 10 phần tử:

- `addPerson(Person)`: trả `false` nếu đối tượng null, `id` rỗng, mảng đầy, hoặc `id` đã tồn tại.
- `findPersonByID(String)`: tìm theo `id`, bỏ qua hoa thường.
- `sortByName()`: sắp xếp nổi bọt theo `fullName`, trả `false` nếu danh sách có 0 hoặc 1 phần tử.
- `login(String code)`: trả về người đầu tiên có `code` khớp và `isActive()` đúng.
- `displayAll()`: in từng người, danh sách rỗng thì in `Danh sach trong!`.

`ZoneManager` giữ `Zone[]` cố định 10 phần tử và chỉ có `addZone(Zone)`. Điều kiện kiểm tra sức chứa đang viết sai: `if (size > area.length)` phải là `>=`, nên khi mảng đầy sẽ ghi ra ngoài mảng và ném `ArrayIndexOutOfBoundsException`.

`IncidentManager` dùng `ArrayList<Incident>` với các phương thức `addIncident`, `updateIncidentStatus`, `deleteIncident`, `searchIncidentById`, `searchIncidentByStatus` và `displayIncidents`. So sánh mã luôn dùng `equalsIgnoreCase`.

Ba lớp quản lý này chưa được gọi từ package `ui` hay từ `Main`, và `AttendanceRecord` cũng chưa xuất hiện ở chỗ nào khác ngoài chính nó. Chưa có lớp quản lý điểm danh tương ứng.

## Đăng nhập và phân quyền

`PersonManager.login(code)` nhận **mã nhân sự** (trường `code`), không phải `id`. Mã trong dữ liệu mẫu:

| Mã | Người | Vai trò (`role`) |
| --- | --- | --- |
| `W001` | Tran Minh Khang | `WORKER` |
| `C001` | Bach Gia Huy | `CONTRACTOR` |
| `V001` | Dang Hai Dang | `VISITOR` |
| `S001` | Pham Van D | `SAFETY_OFFICER` |
| `M001` | Minh Khang | `SITE_MANAGER` |

Bản `Main.java` đã commit trên nhánh `main` có thêm phương thức `canAccess(Person user, String action)` mô tả quyền theo vai trò:

| Vai trò | Hành động được phép |
| --- | --- |
| `WORKER`, `VISITOR` | `CHECK_IN`, `CHECK_OUT`, `VIEW_HISTORY` |
| `SAFETY_OFFICER` | `LOG_INCIDENT`, `UPDATE_INCIDENT` |
| `SITE_MANAGER` | `VIEW_REPORT`, `MANAGE_ZONE`, `MANAGE_PERSON` |

Hàm so khớp vai trò và hành động bằng `equalsIgnoreCase`, trả `false` khi `user` hoặc `action` là null, hoặc khi vai trò không nằm trong danh sách trên. Lưu ý `MenuUserLv03` trong package `ui` lại dùng các chuỗi vai trò khác (`"Visitor"`, `"Worker"`, `"Contractor"`, `"Safety Officier"`, `"Site Manager"`) và không có nhánh nào khớp với `"Visitor"` một cách đầy đủ, nên khi nối menu với `login()` cần thống nhất lại cách viết vai trò.

## Định dạng file dữ liệu

Các file `.txt` ở thư mục gốc là dữ liệu mẫu, hiện chưa có đoạn code nào đọc hay ghi chúng (trong project không có `FileReader`, `FileWriter`, `BufferedReader` hay `java.nio.file`). Các phương thức `toFileLine()` trong lớp mô hình cho thấy ý định ghi ra file theo dấu `|`.

`Person.txt` (5 dòng):

```
WORKER|P001|Tran Minh Khang|W001|ACTIVE|Electrician|BuildCo
CONTRACTOR|P002|Bach Gia Huy|C001|ACTIVE|Concrete Ltd|Short-term
VISITOR|P003|Dang Hai Dang|V001|ACTIVE|Site visit|Manager A
SAFETY_OFFICER|P004|Pham Van D|S001|ACTIVE|SAFE-2026
SITE_MANAGER|P005|Minh Khang|M001|ACTIVE|SmartSite Demo
```

Năm trường đầu theo đúng thứ tự mà `Person.toFileLine()` sinh ra: `role | id | fullName | code | status`. Các trường sau đó là phần riêng của từng lớp con, nhưng số lượng không đồng nhất: dòng `WORKER` và `CONTRACTOR` có 7 trường (thêm công ty và loại hợp đồng), ba dòng còn lại có 6 trường. Khi viết phần đọc file cần xử lý theo vai trò ở trường đầu tiên.

`Zone.txt` (3 dòng):

```
ZONE|Z001|Main Gate|Main entrance and exit area
ZONE|Z002|Storage Area|Material storage area
RESTRICTED_ZONE|Z003|Tower A|High risk construction zone|Permit-A|Safety Officer only
```

Hai dòng đầu theo dạng `loại | zoneId | tên | mô tả`. Dòng thứ ba thêm giấy phép và vai trò được phép ra vào, tức là mô hình `Zone` hiện tại chưa có trường cho hai thông tin này.

`Incident.txt` (2 dòng):

```
IN001|Z003|P004||Worker forgot helmet|OPEN|2026-09-19T09:30:00
IN002|Z002|P004|P001|Blocked emergency path|ASSIGNED|2026-09-19T10:00:00
```

Thứ tự trường khớp với constructor của `Incident`: `incidentId | zoneId | reportId | assigneeID | description | incidentStatus | locateDateTime`. Dòng `IN001` bỏ trống `assigneeID`, dòng `IN002` đã gán cho `P001`. Thời gian ghi theo định dạng `LocalDateTime.toString()`.



