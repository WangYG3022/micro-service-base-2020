package micro.service.cloud.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: WANG Y.G.
 * @time: 2020/09/09 22:42
 * @version: V1.0
 */
@Data
public class SsoUser implements Serializable {
    private static final long serialVersionUID = 3497935890426858541L;

    private String userName;
    private String password;
    private List<String> permissions;

}
