# UML và định hướng thiết kế lại SmartSite

Tài liệu này mô tả cấu trúc đang có trong source hiện tại và đề xuất hướng tổ chức để nhóm thống nhất trước khi chia việc. Sơ đồ đích là đề xuất thiết kế, không khẳng định rằng code hiện tại đã triển khai đầy đủ.

## 1. Mục tiêu thiết kế

Tách rõ bốn phần:

1. **Menu/UI** hiển thị lựa chọn, nhận dữ liệu đầu vào và gọi xử lý.
2. **Manager/Service** thực hiện nghiệp vụ và quản lý các danh sách.
3. **Model** biểu diễn Person, Zone, AttendanceRecord, Incident.
4. **Repository/File storage** đọc
5.  và ghi dữ liệu lâu dài.

Menu gọi nghiệp vụ; nghiệp vụ thao tác model và repository. Model không phụ thuộc menu. Validation diễn ra trước khi lưu hoặc cập nhật.

## 2. UML cấu trúc hiện tại

Sơ đồ tóm tắt các lớp chính trong source hiện tại. Một số nhánh đã có nhưng cách nối vào menu chưa đồng nhất.

~~~mermaid
classDiagram
    class Main
    class WelcomeMenu
    class LoginMenu {
      -PersonManager pm
      -AttendanceManager am
      -CheckPassword checker
      +displayLoginMenu()
    }
    class MenuUserLv00 {
      #Person currentUser
      #PersonManager pm
      #AttendanceManager am
      +displayMenuLv(currentUser, pm, am)
      #handleCommonOption(choice)
      #displayOption()
      #chooseOption()
    }
    class UserSiteManagerMenu
    class UserSafetyOfficerMenu
    class UserWorkerMenu
    class UserContractorMenu
    class UserVisitorMenu
    class UniqueSiteManagerMenu
    class IncidentMenu
    class MenuSession {
      +ATTENDANCE : AttendanceManager
      +ZONES : ZoneManager2
      +INCIDENTS : IncidentManager
    }
    class Login {
      -PersonManager pm
      -CheckPassword checker
      +login(code, password) Person
    }
    class PersonManager {
      -Person[] people
      +addPersonFromInput()
      +findPersonFromInput()
      +deletePersonFromInput()
      +displayAll()
      +sortByName()
      +loadFromFile(path)
    }
    class Person {
      <<abstract>>
      -id
      -fullName
      -code
      -role
      -status
      +isValid() bool
      +isActive() bool
    }
    class Worker
    class Contractor
    class Visitor
    class SafetyOfficer
    class SiteManager
    class AttendanceManager {
      -List~AttendanceRecord~ records
      +checkInFromInput(person, zones)
      +checkOutFromInput(person)
      +displayHistory(personId)
      +displayAll()
    }
    class AttendanceRecord
    class ZoneManager2 {
      -List~Zone~ list
      +addZone()
      +findZoneById(id) Zone
      +updateZoneById()
      +deleteZoneById()
      +addRestrictedZone()
      +displayList()
    }
    class Zone
    class RestrictedZone
    class IncidentManager {
      -List~Incident~ incidents
      +addIncident(...)
      +searchIncidentById(id) Incident
      +updateIncidentStatus(id,status) bool
      +deleteIncident(id) bool
      +displayIncidents()
    }
    class Incident
    class CheckPassword
    class InputScanner
    class Validatable {
      <<interface>>
      +isValid() bool
    }
    class FileManager

    Main --> WelcomeMenu : starts
    WelcomeMenu --> LoginMenu : sign in
    LoginMenu --> Login : verifies
    Login --> PersonManager : finds person
    Login --> CheckPassword : checks credentials
    LoginMenu --> UserSiteManagerMenu : routes by role
    LoginMenu --> UserSafetyOfficerMenu : routes by role
    LoginMenu --> UserWorkerMenu : routes by role
    LoginMenu --> UserContractorMenu : routes by role
    LoginMenu --> UserVisitorMenu : routes by role
    MenuUserLv00 <|-- UserSiteManagerMenu
    MenuUserLv00 <|-- UserSafetyOfficerMenu
    MenuUserLv00 <|-- UserWorkerMenu
    MenuUserLv00 <|-- UserContractorMenu
    MenuUserLv00 <|-- UserVisitorMenu
    UserSiteManagerMenu --> UniqueSiteManagerMenu : HR and Zone
    UserSiteManagerMenu --> IncidentMenu : incident actions
    UniqueSiteManagerMenu --> PersonManager : person actions
    UniqueSiteManagerMenu --> ZoneManager2 : zone actions
    MenuUserLv00 --> AttendanceManager : attendance actions
    MenuSession --> AttendanceManager : shared instance
    MenuSession --> ZoneManager2 : shared instance
    MenuSession --> IncidentManager : shared instance
    IncidentMenu --> IncidentManager : incident actions
    IncidentMenu --> ZoneManager2 : zone lookup
    PersonManager o-- Person : stores
    Person <|-- Worker
    Person <|-- Contractor
    Person <|-- Visitor
    Person <|-- SafetyOfficer
    Person <|-- SiteManager
    Person ..|> Validatable
    ZoneManager2 o-- Zone : stores
    Zone <|-- RestrictedZone
    Zone ..|> Validatable
    AttendanceManager o-- AttendanceRecord : stores
    AttendanceRecord --> Person : personId
    AttendanceRecord --> Zone : zoneId
    IncidentManager o-- Incident : stores
    Incident --> Zone : references
    Incident ..|> Validatable
    PersonManager ..> FileManager : partial persistence
~~~

### Nhận xét về hiện trạng

- currentUser là Person đã đăng nhập. Role dùng để chọn menu; ID dùng cho điểm danh và lịch sử cá nhân.
- AttendanceManager, ZoneManager2 và IncidentManager cần được chia sẻ để menu nhìn cùng dữ liệu. MenuSession giữ các instance này, nhưng cách lấy/truyền Manager chưa đồng nhất ở mọi nhánh.
- PersonManager có singleton. Một số menu lại tự tạo menu con; nhóm nên thống nhất nơi tạo Manager dùng chung.
- FileManager có đọc dòng và nối dòng, nhưng chưa hỗ trợ đầy đủ CRUD bền vững. Phần lớn danh sách đang ở RAM.
- Một số mục menu còn placeholder. Sơ đồ ghi quan hệ chính, không khẳng định mọi lời gọi đã hoàn thiện.

## 3. UML đích đề xuất

Đề xuất Main tạo một AppContext chứa Manager dùng chung. Sau khi đăng nhập, AppSession giữ currentUser cùng tham chiếu context. Có thể đổi tên hai lớp này. Ý chính là tạo dependency một lần rồi truyền xuống menu.

~~~mermaid
classDiagram
    class Main
    class AppContext {
      +PersonManager people
      +AttendanceManager attendance
      +ZoneManager zones
      +IncidentManager incidents
      +AccountService accounts
    }
    class AppSession {
      +Person currentUser
      +AppContext app
      +hasRole(role) bool
    }
    class LoginController {
      +login(code,password) AppSession
    }
    class MenuRouter {
      +openMenu(session)
    }
    class BaseMenu {
      #AppSession session
      +display()
    }
    class SiteManagerMenu
    class SafetyOfficerMenu
    class WorkerMenu
    class ContractorMenu
    class VisitorMenu
    class PersonMenu
    class ZoneMenu
    class AttendanceMenu
    class IncidentMenu
    class PersonService {
      +add(person) Result
      +findById(id) Person
      +findByCode(code) Person
      +update(id, changes) Result
      +delete(id) Result
      +list() List~Person~
    }
    class ZoneService
    class AttendanceService {
      +checkIn(personId, zoneId, pin) Result
      +checkOut(personId) Result
      +history(personId) List~AttendanceRecord~
    }
    class IncidentService
    class AccountService
    class PersonRepository {
      <<interface>>
      +findById(id) Person
      +save(person) Result
      +delete(id) Result
      +findAll() List~Person~
    }
    class ZoneRepository
    class AttendanceRepository
    class IncidentRepository
    class FilePersonRepository
    class Person
    class Worker
    class Contractor
    class Visitor
    class SafetyOfficer
    class SiteManager
    class Zone
    class RestrictedZone
    class AttendanceRecord
    class Incident
    class Validator

    Main --> AppContext : creates once
    Main --> LoginController : starts
    LoginController --> AppContext : uses
    LoginController --> AppSession : returns after authentication
    MenuRouter --> AppSession : routes by currentUser.role
    BaseMenu <|-- SiteManagerMenu
    BaseMenu <|-- SafetyOfficerMenu
    BaseMenu <|-- WorkerMenu
    BaseMenu <|-- ContractorMenu
    BaseMenu <|-- VisitorMenu
    SiteManagerMenu --> PersonMenu
    SiteManagerMenu --> ZoneMenu
    SiteManagerMenu --> AttendanceMenu
    SiteManagerMenu --> IncidentMenu
    SafetyOfficerMenu --> IncidentMenu
    AppContext --> PersonService
    AppContext --> ZoneService
    AppContext --> AttendanceService
    AppContext --> IncidentService
    AppContext --> AccountService
    PersonMenu --> PersonService
    ZoneMenu --> ZoneService
    AttendanceMenu --> AttendanceService
    IncidentMenu --> IncidentService
    PersonService --> PersonRepository
    ZoneService --> ZoneRepository
    AttendanceService --> AttendanceRepository
    IncidentService --> IncidentRepository
    FilePersonRepository ..|> PersonRepository
    PersonService ..> Validator
    ZoneService ..> Validator
    Person <|-- Worker
    Person <|-- Contractor
    Person <|-- Visitor
    Person <|-- SafetyOfficer
    Person <|-- SiteManager
    Zone <|-- RestrictedZone
    AttendanceService --> PersonService : validates person
    AttendanceService --> ZoneService : validates zone and PIN
    IncidentService --> ZoneService : validates zone
~~~

## 4. Gọi đối tượng trong menu

Nhóm nên chọn một trong hai phương án và dùng xuyên suốt.

### A. Truyền context/session

~~~text
Main tạo AppContext một lần
  → LoginController xác thực
  → thành công tạo AppSession(currentUser, appContext)
  → MenuRouter mở menu theo currentUser.role
  → menu gọi Manager/Service qua session.app
~~~

Menu nhận một đối tượng gọn. AppSession giữ người đăng nhập; AppContext giữ Manager dùng chung. Context chỉ gom dependency, không chứa nghiệp vụ.

### B. Truyền từng Manager

~~~text
Main tạo Manager dùng chung một lần
  → truyền các Manager vào menu qua constructor
  → truyền currentUser vào menu có chức năng theo người dùng
~~~

Cách này cho thấy rõ menu cần những Manager nào, nhưng constructor có thể nhiều tham số hơn.

Cả hai cách đều tránh tạo Manager mới ngay trong menu. Nếu mỗi menu gọi new Manager(), danh sách mới có thể rỗng và không thấy dữ liệu ở menu khác.

currentUser có vai trò khác role:

- role quyết định điều hướng và quyền.
- currentUser.id xác định người cho điểm danh/lịch sử.
- currentUser chứa thông tin để hiển thị.
- code là thông tin đăng nhập; không thay thế Person ID hoặc role.

## 5. Trách nhiệm từng phần

| Thành phần | Trách nhiệm |
|---|---|
| Main | Tạo dependency dùng chung và bắt đầu ứng dụng |
| LoginController/LoginService | Xác thực, tìm Person, kiểm tra trạng thái |
| MenuRouter | Chọn menu theo role đã xác thực |
| Menu | Hiển thị, nhận lựa chọn, gọi nghiệp vụ, trình bày kết quả |
| PersonManager | CRUD Person, trùng ID/code, tìm kiếm, sắp xếp |
| ZoneManager | CRUD Zone, kiểm tra khu vực hạn chế |
| AttendanceManager | Check-in/out, PIN, lượt đang mở, lịch sử |
| IncidentManager | CRUD Incident và tìm kiếm |
| AccountService | Xác thực và đồng bộ code tài khoản |
| Validator | Kiểm tra định dạng và trường bắt buộc |
| Repository | Đọc/ghi đối tượng theo schema lưu trữ |
| Model | Thuộc tính và hành vi gắn với từng đối tượng |

Manager nên trả kết quả thành công/thất bại và thông tin lỗi; menu hiển thị thông báo. Như vậy nghiệp vụ không phụ thuộc System.out.

## 6. Luồng nghiệp vụ đề xuất

### Đăng nhập

~~~text
WelcomeMenu
  → LoginController.authenticate(code, password)
  → AccountService kiểm tra credential
  → PersonManager tìm Person theo code
  → kiểm tra Person active
  → tạo AppSession
  → MenuRouter chọn menu theo role
~~~

### Cập nhật Person

~~~text
PersonMenu nhập ID và thay đổi
  → PersonManager tìm Person
  → Validator kiểm tra dữ liệu mới
  → Manager kiểm tra trùng và quy tắc nghiệp vụ
  → chỉ cập nhật khi toàn bộ đầu vào hợp lệ
  → Repository lưu thành công
  → menu hiển thị kết quả
~~~

Giai đoạn đầu nên giữ nguyên ID và role. Đổi role có thể yêu cầu đổi loại đối tượng; đổi code cần đồng bộ tài khoản.

### Check-in khu vực hạn chế

~~~text
AttendanceMenu
  → AttendanceManager.checkIn(personId, zoneId, pin)
  → xác nhận Person tồn tại và active
  → xác nhận chưa có lượt mở
  → tìm Zone
  → nếu RestrictedZone thì kiểm tra PIN
  → tạo AttendanceRecord
  → Repository lưu
~~~

### Lưu/nạp dữ liệu

~~~text
Khởi động → Repository nạp → Manager dùng dữ liệu trong phiên
Thay đổi thành công → Repository ghi → menu báo kết quả
~~~

Nếu dùng file văn bản, cần thống nhất schema, encoding, dấu phân cách, file sai định dạng và cách ghi toàn bộ danh sách khi cập nhật/xóa. Chỉ nối thêm dòng không đủ cho CRUD vì có thể tạo bản ghi trùng hoặc giữ lại dữ liệu đã xóa.

## 7. Quyết định cần chốt trước khi chia việc

1. Dùng AppSession/AppContext hay truyền riêng từng Manager?
2. Manager nào sống suốt phiên và được tạo ở đâu?
3. Định dạng Person ID, login code, Zone ID và Incident ID là gì?
4. Role/status dùng chuỗi hay enum/hằng số?
5. Thêm Person có tự cấp tài khoản không?
6. Đổi code có đồng bộ tài khoản không?
7. Dữ liệu lưu tự động hay qua mục Save?
8. Có chặn xóa Person/Zone khi bản ghi khác tham chiếu không?
9. Validator kiểm tra gì; Manager kiểm tra trùng/liên kết gì?
10. Hàm nghiệp vụ trả boolean hay đối tượng kết quả có thông báo lỗi?

## 8. Thứ tự triển khai

1. Chốt UML, tên lớp, khóa ID và chữ ký API.
2. Chuẩn hóa model và validation.
3. Hoàn thiện Person CRUD, gồm cập nhật.
4. Hoàn thiện lưu/nạp Person và Account.
5. Hoàn thiện Zone CRUD và thống nhất/migrate dữ liệu Zone cũ.
6. Hoàn thiện Attendance và Incident bằng ID tham chiếu.
7. Nối menu vào Manager/Service dùng chung.
8. Kiểm tra luồng: đăng nhập → thao tác → lưu → khởi động lại → xác nhận dữ liệu.

Có thể chia nhóm theo Person, Zone, Attendance, Incident và Persistence. Một người phụ trách tích hợp Main/context/điều hướng. Mọi người cùng dùng API đã chốt để tránh sửa chéo.

## 9. Backlog cần lưu ý

- Cập nhật Person chưa có nghiệp vụ hoàn chỉnh.
- Manager chủ yếu giữ dữ liệu RAM; lưu/nạp đầy đủ chưa hoàn tất.
- Tài khoản và Person ở hai nguồn; thêm Person hoặc đổi login code cần đồng bộ.
- Một số menu role còn placeholder.
- Quy tắc Zone ID và dữ liệu cũ cần rà soát/migrate cùng nhau.
- FileManager.appendFile() không đáp ứng cập nhật/xóa toàn danh sách.
- Cần quyết định xóa thế nào khi Attendance/Incident tham chiếu Person/Zone.
- Một số Manager còn nhập liệu và in thông báo; nên tách UI và nghiệp vụ dần.

## 10. Nguyên tắc chung

- Khởi tạo Manager dùng chung một lần.
- Menu không sở hữu danh sách nghiệp vụ và không tự tạo Manager.
- currentUser.role dùng để điều hướng; currentUser.id dùng cho thao tác theo người.
- Validator kiểm tra định dạng; Manager kiểm tra nghiệp vụ và tính duy nhất.
- Repository chịu trách nhiệm lưu/nạp.
- Chỉ cập nhật dữ liệu sau khi tất cả đầu vào hợp lệ.
- Chốt chữ ký hàm và schema trước khi làm song song.
