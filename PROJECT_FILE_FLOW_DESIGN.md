# Định hướng thiết kế lại SmartSite: file flow và UML

Tài liệu này mô tả hướng thiết kế theo yêu cầu nhóm. Đây là đề xuất; source hiện tại chưa được thay theo sơ đồ đích.

## 1. Mục tiêu

- File là nguồn dữ liệu lâu dài.
- Display đọc file mới nhất rồi hiển thị.
- Add/update/delete đọc dữ liệu hiện tại, áp dụng nghiệp vụ, ghi file; sau khi ghi thành công thì đọc lại và hiển thị kết quả.
- Đăng nhập thành công mới tạo currentUser. Role dùng để chọn menu; ID dùng trong tác vụ gắn với người.
- Bỏ MenuSession trong thiết kế mới.
- MenuUserLv00 không giữ pm hoặc am.
- Person, Zone, Incident và Attendance là các module riêng; không dồn logic vào Site Manager.

## 2. Luồng tổng thể

~~~mermaid
flowchart TD
    A[Main] --> B[WelcomeMenu]
    B --> C[Nhập code]
    C --> D[Nhập password]
    D --> E[LoginController xác thực]
    E --> F{Đúng?}
    F -- Không --> C
    F -- Có --> G[Tìm Person theo code, kiểm tra ACTIVE]
    G --> H{Hợp lệ?}
    H -- Không --> C
    H -- Có --> I[Tạo currentUser]
    I --> J[MenuRouter đọc currentUser.role]
    J --> K[Menu theo role]
    K --> L[Module nghiệp vụ có quyền]
    L --> M[Service đọc file mới nhất]
    M --> N[Validate và xử lý]
    N --> O[Ghi file]
    O --> P[Đọc lại và hiển thị kết quả]
    P --> K
~~~

Ba trường có vai trò khác nhau:

- code dùng đăng nhập.
- role chọn menu và quyền.
- id liên kết Person với Attendance/Incident và xác định người thực hiện.

Không suy role từ code khi currentUser đã có role. Truyền currentUser hoặc currentUser.id vào thao tác nào cần xác định người dùng.

## 3. UML hiện trạng trong source

Sơ đồ tóm tắt các lớp chính. Nó không khẳng định mọi lời gọi đã nối hoàn chỉnh ở từng menu.

~~~mermaid
classDiagram
    class Main
    class WelcomeMenu
    class LoginMenu
    class Login
    class MenuUserLv00 {
      #currentUser : Person
      #pm : PersonManager
      #am : AttendanceManager
      +displayMenuLv(currentUser, pm, am)
      #handleCommonOption(choice)
    }
    class UserSiteManagerMenu
    class UserSafetyOfficerMenu
    class UserWorkerMenu
    class UserContractorMenu
    class UserVisitorMenu
    class UniqueSiteManagerMenu
    class MenuSession
    class IncidentMenu
    class PersonManager
    class AttendanceManager
    class ZoneManager2
    class IncidentManager
    class Person {
      <<abstract>>
      id
      fullName
      code
      role
      status
    }
    class Worker
    class Contractor
    class Visitor
    class SafetyOfficer
    class SiteManager
    class Zone
    class RestrictedZone
    class AttendanceRecord
    class Incident
    class Validatable {
      <<interface>>
      isValid()
    }
    class FileManager
    class CheckPassword

    Main --> WelcomeMenu
    WelcomeMenu --> LoginMenu
    LoginMenu --> Login
    Login --> PersonManager
    Login --> CheckPassword
    LoginMenu --> UserSiteManagerMenu : role routing
    LoginMenu --> UserSafetyOfficerMenu : role routing
    LoginMenu --> UserWorkerMenu : role routing
    LoginMenu --> UserContractorMenu : role routing
    LoginMenu --> UserVisitorMenu : role routing
    MenuUserLv00 <|-- UserSiteManagerMenu
    MenuUserLv00 <|-- UserSafetyOfficerMenu
    MenuUserLv00 <|-- UserWorkerMenu
    MenuUserLv00 <|-- UserContractorMenu
    MenuUserLv00 <|-- UserVisitorMenu
    UserSiteManagerMenu --> UniqueSiteManagerMenu
    UserSiteManagerMenu --> IncidentMenu
    UniqueSiteManagerMenu --> PersonManager
    UniqueSiteManagerMenu --> ZoneManager2
    MenuUserLv00 --> AttendanceManager
    MenuSession --> AttendanceManager
    MenuSession --> ZoneManager2
    MenuSession --> IncidentManager
    IncidentMenu --> IncidentManager
    PersonManager o-- Person
    Person <|-- Worker
    Person <|-- Contractor
    Person <|-- Visitor
    Person <|-- SafetyOfficer
    Person <|-- SiteManager
    Person ..|> Validatable
    ZoneManager2 o-- Zone
    Zone <|-- RestrictedZone
    Zone ..|> Validatable
    AttendanceManager o-- AttendanceRecord
    IncidentManager o-- Incident
    Incident --> Zone
    Incident ..|> Validatable
~~~

Hiện MenuUserLv00 giữ currentUser, pm và am; MenuSession giữ các Manager static. PersonManager có singleton, và một số menu tự khởi tạo menu con. Đây là nhiều cách quản lý dependency cùng tồn tại. FileManager mới có đọc dòng/append, chưa đáp ứng đầy đủ update/delete; phần lớn danh sách nằm trong RAM.

## 4. UML thiết kế đích

Main tạo AppContext một lần. AppContext giữ service dùng chung; AppSession biểu diễn lần đăng nhập với currentUser. Nhóm có thể chọn tên khác, miễn giữ quy tắc tạo một lần và truyền xuống nơi cần.

~~~mermaid
classDiagram
    class Main
    class AppContext {
      +personService
      +zoneService
      +attendanceService
      +incidentService
      +accountService
    }
    class AppSession {
      +currentUser : Person
      +app : AppContext
    }
    class LoginController {
      +authenticate(code,password) LoginResult
    }
    class MenuRouter {
      +openFor(session)
    }
    class BaseRoleMenu {
      #currentUser : Person
      +display()
      +handleChoice(choice)
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
      +update(id,changes) Result
      +delete(id) Result
      +readAll() List
    }
    class ZoneService
    class AttendanceService {
      +checkIn(personId,zoneId,pin) Result
      +checkOut(personId) Result
      +history(personId) List
    }
    class IncidentService
    class AccountService
    class PersonRepository {
      <<interface>>
      +readAll()
      +writeAll(list)
    }
    class ZoneRepository
    class AttendanceRepository
    class IncidentRepository
    class FilePersonRepository
    class FileZoneRepository
    class FileAttendanceRepository
    class FileIncidentRepository
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
    Main --> WelcomeMenu
    WelcomeMenu --> LoginController
    LoginController --> AccountService
    LoginController --> PersonService
    LoginController --> AppSession : successful authentication
    MenuRouter --> AppSession : reads role
    BaseRoleMenu <|-- SiteManagerMenu
    BaseRoleMenu <|-- SafetyOfficerMenu
    BaseRoleMenu <|-- WorkerMenu
    BaseRoleMenu <|-- ContractorMenu
    BaseRoleMenu <|-- VisitorMenu
    SiteManagerMenu --> PersonMenu : if authorized
    SiteManagerMenu --> ZoneMenu : if authorized
    SiteManagerMenu --> IncidentMenu : if authorized
    SafetyOfficerMenu --> IncidentMenu : if authorized
    BaseRoleMenu --> AttendanceMenu
    PersonMenu --> PersonService
    ZoneMenu --> ZoneService
    AttendanceMenu --> AttendanceService
    IncidentMenu --> IncidentService
    AppContext --> PersonService
    AppContext --> ZoneService
    AppContext --> AttendanceService
    AppContext --> IncidentService
    AppContext --> AccountService
    PersonService --> PersonRepository
    ZoneService --> ZoneRepository
    AttendanceService --> AttendanceRepository
    IncidentService --> IncidentRepository
    FilePersonRepository ..|> PersonRepository
    FileZoneRepository ..|> ZoneRepository
    FileAttendanceRepository ..|> AttendanceRepository
    FileIncidentRepository ..|> IncidentRepository
    PersonService ..> Validator
    ZoneService ..> Validator
    Person <|-- Worker
    Person <|-- Contractor
    Person <|-- Visitor
    Person <|-- SafetyOfficer
    Person <|-- SiteManager
    Zone <|-- RestrictedZone
~~~

Site Manager có thể được phân quyền truy cập nhiều module, nhưng code nghiệp vụ vẫn thuộc Person/Zone/Incident service riêng. Safety Officer có thể được cấp Incident Management. Nhóm cần chốt quyền chính thức bằng ma trận role-function.

## 5. Chu trình đọc/ghi file khép kín

### Display

~~~text
Menu chọn Display
  → Service.readAll()
  → Repository đọc file mới nhất
  → parse thành danh sách model
  → Menu hiển thị
~~~

### Add Person

~~~mermaid
sequenceDiagram
    actor User
    participant Menu as PersonMenu
    participant Service as PersonService
    participant Validator
    participant Repo as PersonRepository/File

    User->>Menu: Nhập Person
    Menu->>Service: add(person)
    Service->>Repo: readAll()
    Repo-->>Service: danh sách hiện tại
    Service->>Validator: kiểm tra định dạng
    Validator-->>Service: hợp lệ/không hợp lệ
    Service->>Service: kiểm tra ID/code trùng
    Service->>Repo: writeAll(danh sách mới)
    Repo-->>Service: kết quả ghi
    Service-->>Menu: Result
    Menu->>Service: readAll()
    Service->>Repo: đọc lại file
    Repo-->>Menu: danh sách mới nhất
    Menu-->>User: Hiển thị cả Person vừa thêm
~~~

Update/delete thực hiện tương tự: đọc file mới nhất, tìm đối tượng, kiểm tra dữ liệu, áp dụng thay đổi, ghi lại toàn bộ file, đọc lại để xác nhận.

Nếu ghi thất bại, không báo thành công. Nên ghi file tạm trước rồi thay file chính sau khi hoàn tất. Append một dòng không đủ cho update/delete vì có thể để lại bản cũ hoặc sinh bản ghi trùng.

## 6. Bỏ pm/am khỏi MenuUserLv00

Base menu chỉ giữ currentUser và hành vi giao diện chung như vòng lặp, hiển thị lựa chọn. Nó không giữ PersonManager hoặc AttendanceManager.

- Khi chọn Person Management, mở PersonMenu và cung cấp PersonService cho module đó.
- Khi chọn Zone Management, mở ZoneMenu với ZoneService.
- Khi chọn Incident Management, mở IncidentMenu với IncidentService.
- Khi chọn Attendance, gọi AttendanceService và truyền currentUser hoặc currentUser.id ở đúng thao tác.

Ví dụ luồng:

~~~text
BaseRoleMenu nhận currentUser
  → chọn Check-in
  → AttendanceMenu gọi checkIn(currentUser.id, zoneId, pin)

BaseRoleMenu không cần biến am.
PersonMenu gọi PersonService.add(person).
BaseRoleMenu không cần biến pm.
~~~

Dependency được truyền vào module qua constructor hoặc factory. Không tạo Manager mới ở mỗi lần mở menu. AppContext có thể giữ service dùng chung mà không biến thành nơi chứa nghiệp vụ.

## 7. Không dồn chức năng vào Site Manager

| Module | Trách nhiệm |
|---|---|
| PersonMenu | Add, find, update, delete, display Person |
| ZoneMenu | Add, find, update, delete, display Zone |
| IncidentMenu | Add, find, update status, delete, filter/display Incident |
| AttendanceMenu | Check-in, check-out, lịch sử |
| Role menus | Hiển thị chức năng được phép, mở module tương ứng |

Ví dụ ma trận quyền để nhóm thảo luận:

| Chức năng | Site Manager | Safety Officer | Worker/Contractor/Visitor |
|---|---:|---:|---:|
| Thông tin cá nhân | Có | Có | Có |
| Check-in/out | Có | Có | Có |
| Lịch sử cá nhân | Có | Có | Có |
| Quản lý Person | Nhóm chốt | Nhóm chốt | Không |
| Quản lý Zone | Nhóm chốt | Nhóm chốt | Không |
| Quản lý Incident | Có thể | Có thể | Theo yêu cầu |
| Lịch sử toàn hệ thống | Có thể | Có thể | Không |

Các ô “Nhóm chốt/Có thể” là quyết định cần nhóm thống nhất, không phải quyền đã xác nhận trong source.

## 8. Account và Person

Account và Person có thể nằm ở hai file nhưng cần một khóa liên kết thống nhất, thường là login code.

- Add Person: quyết định tạo account cùng lúc hay cấp account riêng.
- Đổi code: cập nhật account và Person cùng một quy trình.
- Nếu một file ghi thành công còn file kia thất bại, phải có cách khôi phục.
- Giai đoạn đầu có thể giữ code bất biến trong Update Person.

## 9. API nên thống nhất trước khi chia việc

Các chữ ký là gợi ý:

~~~text
PersonRepository.readAll() -> List<Person>
PersonRepository.writeAll(List<Person>) -> Result

PersonService.addPerson(Person) -> Result
PersonService.findById(String) -> Person
PersonService.updatePerson(String id, PersonChanges) -> Result
PersonService.deletePerson(String id) -> Result
PersonService.readAll() -> List<Person>

AttendanceService.checkIn(String personId, String zoneId, String pin) -> Result
AttendanceService.checkOut(String personId) -> Result
AttendanceService.history(String personId) -> List<AttendanceRecord>
~~~

Result có thể chứa success, message và dữ liệu trả về. Nhóm có thể dùng boolean nếu muốn đơn giản, nhưng thống nhất cách xử lý lỗi giữa các service.

Gợi ý chia việc: Person CRUD/validation; Repository/schema; Zone; Attendance/Incident; một người tích hợp Main, login, router và quyền.

## 10. Quyết định cần chốt

1. Dùng AppContext/AppSession hay truyền từng service?
2. File nào là nguồn chuẩn cho từng loại?
3. Schema, encoding, dấu phân cách và xử lý file sai?
4. Ghi file toàn bộ hay append có version?
5. Add Person cấp account cùng lúc hay riêng?
6. Cho phép đổi login code không?
7. Xóa Person/Zone đang được Attendance/Incident tham chiếu thì làm gì?
8. Role nào được mở từng module?
9. Định dạng ID chuẩn; dữ liệu Zone cũ có cần chuyển đổi?
10. Ghi file thất bại thì báo/hoàn tác ra sao?

## 11. Lộ trình

1. Chốt UML, ma trận role-function, schema file và API.
2. Thiết kế nơi khởi tạo dependency và router; bỏ MenuSession trong thiết kế mới.
3. Làm Repository và kiểm tra đọc/ghi round-trip.
4. Hoàn thiện Person CRUD khép kín: add → write → read → display; sau đó update/delete/find.
5. Hoàn thiện Zone CRUD và PIN khu vực hạn chế.
6. Hoàn thiện Attendance và Incident theo ID tham chiếu.
7. Nối module vào menu theo quyền; BaseRoleMenu không giữ pm/am.
8. Kiểm tra đăng nhập, thao tác, file sau thao tác, khởi động lại và hiển thị lại.

## 12. Backlog từ source hiện tại

- MenuSession đang tồn tại nhưng thiết kế mới muốn bỏ.
- MenuUserLv00 hiện giữ currentUser, pm và am.
- Manager được khởi tạo/chia sẻ theo nhiều cách.
- Update Person và lưu CRUD đầy đủ chưa hoàn thiện.
- Account/Person cần đồng bộ khi add/đổi code.
- Quy tắc Zone ID và dữ liệu cũ cần thống nhất/migrate.
- FileManager append không giải quyết update/delete.
- Cần quy tắc xóa khi dữ liệu khác tham chiếu Person/Zone.
- Một số menu role còn placeholder.
- Một số Manager gộp nhập liệu, nghiệp vụ và in thông báo; nên tách dần.

## 13. Nguyên tắc nhóm

- File là nguồn chuẩn trong thiết kế file-driven.
- Mọi thao tác thay đổi đọc dữ liệu mới nhất trước khi xử lý.
- Chỉ báo thành công sau khi ghi file thành công.
- Hiển thị sau thao tác phản ánh nội dung file mới nhất.
- Menu không tạo Manager; dependency dùng chung được tạo ở tầng ứng dụng.
- currentUser xác định người dùng và role; service nhận ID/đối tượng cần cho nghiệp vụ.
- Chốt API, schema và quyền trước khi chia việc song song.
