import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate, useParams } from "react-router-dom";
import { noticeRead } from "../../lib/withTokenApi";
import { setNotice } from "../../store/notice";
import { RootState } from "../../store/store";
import styles from "./NoticeDetailPage.module.scss";

const NoticeDetailPage: React.FC = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { noticeId } = useParams();

  const notice = useSelector((state: RootState) => state.notice.notice);

  useEffect(() => {
    noticeRead(Number(noticeId))
      .then((res) => {
        // dispatch(setNoticeList(res))
        dispatch(setNotice(res));
      })
      .catch((err) => {
        console.log("err", err);
      });
  }, []);

  return (
    <div className={styles.container}>
      <div className={styles.noticeDetail}>
        <div className={styles.title}>공지사항</div>
        {/* <div>{notice.noticeId}</div> */}
        <div>
          <div className={styles.noticeTitle}>
            <div>{notice.title}</div>
            <div>
              {new Date(notice.createdDate!).toLocaleDateString("ko-Kr", {
                weekday: "long",
                year: "numeric",
                month: "long",
                day: "numeric",
              })}
            </div>
          </div>
          <div className={styles.noticeContent}>{notice.content}</div>
          <div className={styles.noticeBottom}>
            <button onClick={() => navigate(-1)} className={styles.navigateBtn}>
              뒤로가기
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default NoticeDetailPage;
