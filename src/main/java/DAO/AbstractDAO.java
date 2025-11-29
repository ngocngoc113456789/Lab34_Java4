package DAO;

import java.util.List;

public interface AbstractDAO<T> {

    /**
     * Chèn một Entity MỚI vào cơ sở dữ liệu (PERSIST).
     * @param entity Đối tượng Entity cần thêm mới.
     * @return Entity đã được chèn.
     */
    T insert(T entity); // Đổi tên từ create thành insert để rõ ràng hơn

    /**
     * Cập nhật một Entity đã tồn tại trong cơ sở dữ liệu (MERGE).
     * @param entity Đối tượng Entity cần cập nhật.
     * @return Entity đã được cập nhật.
     */
    T update(T entity); // <-- Đã thêm phương thức UPDATE

    /**
     * Xóa một Entity khỏi cơ sở dữ liệu.
     * @param entity Đối tượng Entity cần xóa (thường lấy từ findById trước).
     * @return Entity đã bị xóa (hoặc null nếu không tìm thấy).
     */
    T delete(T entity);

    /**
     * Tìm một Entity bằng ID của nó.
     * @param id Khóa chính của Entity.
     * @return Entity tương ứng, hoặc null nếu không tìm thấy.
     */
    T findById(Object id);

    /**
     * Lấy tất cả các Entity.
     * @return Danh sách tất cả các Entity.
     */
    List<T> findAll();

    /**
     * Lấy tất cả các Entity có phân trang.
     * @param page Số trang (bắt đầu từ 1).
     * @param pageSize Số lượng bản ghi trên mỗi trang.
     * @return Danh sách các Entity của trang được yêu cầu.
     */
    List<T> findAll(int page, int pageSize);

    /**
     * Thực hiện một truy vấn NamedQuery và trả về danh sách các Entity.
     * @param name Tên của NamedQuery.
     * @param params Mảng các tham số (ví dụ: key, value).
     * @return Danh sách các Entity tìm được.
     */
    List<T> findByNamedQuery(String name, Object... params);

    /**
     * Thực hiện một truy vấn NamedQuery và trả về một Entity duy nhất.
     * @param name Tên của NamedQuery.
     * @param params Mảng các tham số.
     * @return Entity duy nhất tìm được, hoặc null.
     */
    T findSingleByNamedQuery(String name, Object... params);

    /**
     * Thực hiện một truy vấn NamedQuery để đếm tổng số lượng bản ghi.
     * @param name Tên của NamedQuery (thường là COUNT query).
     * @param params Mảng các tham số.
     * @return Tổng số lượng bản ghi (Long).
     */
    Long countByNamedQuery(String name, Object... params);
}