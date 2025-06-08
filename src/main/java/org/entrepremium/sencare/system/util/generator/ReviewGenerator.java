package org.entrepremium.sencare.system.util.generator;

import org.entrepremium.sencare.feature.doctor.Doctor;
import org.entrepremium.sencare.feature.hospital.Hospital;
import org.entrepremium.sencare.feature.hosserv.HosServ;
import org.entrepremium.sencare.feature.myuser.MyUser;
import org.entrepremium.sencare.feature.review.Review;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ReviewGenerator {

    private static final Random random = new Random();

    // Vietnamese review templates for different ratings
    private static final List<String> EXCELLENT_REVIEWS = Arrays.asList(
            "Dịch vụ rất tuyệt vời, bác sĩ nhiệt tình và chuyên nghiệp. Tôi rất hài lòng với chất lượng khám chữa bệnh.",
            "Bệnh viện có cơ sở vật chất hiện đại, nhân viên y tế tận tâm. Quy trình khám bệnh nhanh chóng và hiệu quả.",
            "Bác sĩ rất chu đáo, giải thích rõ ràng về tình trạng bệnh. Điều dưỡng viên cũng rất quan tâm chăm sóc bệnh nhân.",
            "Chất lượng dịch vụ xuất sắc, không gian sạch sẽ, thoáng mát. Thời gian chờ đợi không quá lâu.",
            "Đội ngũ y bác sĩ giàu kinh nghiệm, thiết bị y tế tiên tiến. Tôi hoàn toàn tin tưởng vào chất lượng điều trị.",
            "Dịch vụ khám bệnh chuyên nghiệp, bác sĩ tận tình tư vấn. Giá cả hợp lý, đáng đồng tiền bát gạo.",
            "Bệnh viện có môi trường thân thiện, nhân viên lịch sự. Kết quả điều trị rất tốt, tôi rất biết ơn.",
            "Quy trình khám chữa bệnh khoa học, bác sĩ có tay nghề cao. Tôi sẽ giới thiệu cho bạn bè, người thân."
    );

    private static final List<String> GOOD_REVIEWS = Arrays.asList(
            "Dịch vụ tốt, bác sĩ nhiệt tình nhưng thời gian chờ hơi lâu. Nhìn chung vẫn hài lòng với chất lượng.",
            "Chất lượng khám chữa bệnh ổn, giá cả phải chăng. Có thể cải thiện thêm về thái độ phục vụ.",
            "Bác sĩ chuyên môn tốt, giải thích rõ ràng. Cần cải thiện thêm về cơ sở vật chất và không gian chờ.",
            "Đội ngũ y tế có kinh nghiệm, điều trị hiệu quả. Mong bệnh viện đầu tư thêm về trang thiết bị.",
            "Dịch vụ tương đối tốt, nhân viên thân thiện. Cần rút ngắn thời gian chờ đợi để phục vụ tốt hơn.",
            "Chất lượng khám bệnh ổn định, bác sĩ tận tâm. Có thể cải thiện thêm về quy trình đăng ký khám.",
            "Môi trường sạch sẽ, nhân viên lịch sự. Kết quả điều trị tốt nhưng chi phí hơi cao một chút."
    );

    private static final List<String> AVERAGE_REVIEWS = Arrays.asList(
            "Dịch vụ bình thường, không có gì đặc biệt. Bác sĩ khám nhanh nhưng chưa tư vấn kỹ lưỡng.",
            "Chất lượng trung bình, thời gian chờ đợi khá lâu. Nhân viên thái độ chưa được nhiệt tình lắm.",
            "Cơ sở vật chất cũ kỹ, cần được đầu tư nâng cấp. Bác sĩ có kinh nghiệm nhưng ít giao tiếp.",
            "Giá cả hợp lý nhưng chất lượng dịch vụ chưa tương xứng. Cần cải thiện nhiều về quy trình.",
            "Khám bệnh nhanh chóng nhưng thiếu sự quan tâm đến bệnh nhân. Không gian chờ đợi chật chội.",
            "Đội ngũ y tế có chuyên môn nhưng thái độ phục vụ cần được cải thiện thêm nhiều.",
            "Dịch vụ tạm được, không quá tệ nhưng cũng không ấn tượng. Mong có sự thay đổi tích cực."
    );

    private static final List<String> POOR_REVIEWS = Arrays.asList(
            "Dịch vụ kém, thái độ nhân viên không thân thiện. Thời gian chờ đợi quá lâu, quy trình rườm rà.",
            "Chất lượng khám chữa bệnh không như mong đợi. Bác sĩ thiếu tận tâm, tư vấn sơ sài.",
            "Cơ sở vật chất cũ kỹ, không gian chật chội. Chi phí cao nhưng chất lượng dịch vụ thấp.",
            "Quy trình khám bệnh lộn xộn, nhân viên thiếu chuyên nghiệp. Kết quả điều trị chưa rõ ràng.",
            "Thái độ phục vụ tệ, bác sĩ ít quan tâm đến bệnh nhân. Mong bệnh viện cải thiện chất lượng.",
            "Dịch vụ không đáng đồng tiền, thiết bị y tế lạc hậu. Không khuyến khích mọi người đến đây.",
            "Môi trường không sạch sẽ, nhân viên lười biếng. Chất lượng khám chữa bệnh rất đáng thất vọng."
    );

    private static final List<String> TERRIBLE_REVIEWS = Arrays.asList(
            "Dịch vụ tệ hại, thái độ nhân viên rất tồi tệ. Hoàn toàn không hài lòng với chất lượng điều trị.",
            "Chất lượng cực kỳ tệ, bác sĩ thiếu trách nhiệm. Lãng phí thời gian và tiền bạc của bệnh nhân.",
            "Cơ sở vật chất xuống cấp nghiêm trọng, dịch vụ tệ không thể chấp nhận được.",
            "Quy trình lộn xộn, nhân viên vô trách nhiệm. Kết quả điều trị không như cam kết ban đầu.",
            "Thái độ phục vụ cực kỳ tệ, bác sĩ không quan tâm đến bệnh nhân. Rất thất vọng và tức giận.",
            "Dịch vụ tệ nhất từng trải qua, không khuyến khích ai đến đây khám chữa bệnh.",
            "Môi trường bẩn thỉu, nhân viên kỳ thị bệnh nhân. Chất lượng điều trị dưới mức trung bình."
    );

    /**
     * Generate a random review for a hospital service
     */
    private static Review generateHospitalServiceReview(HosServ hosServ, MyUser reviewer, Hospital hospital) {
        Review review = new Review();

        double rating = generateWeightedRating();
        String content = generateReviewContent(rating);

        review.setHosServ(hosServ);
        review.setHospital(hospital);
        review.setCreatedBy(reviewer);
        review.setRating(rating);
        review.setContent(content);

        return review;
    }

    /**
     * Generate a random review for a doctor
     */
    private static Review generateDoctorReview(Doctor doctor, MyUser reviewer, Hospital hospital) {
        Review review = new Review();

        double rating = generateWeightedRating();
        String content = generateReviewContent(rating);

        review.setDoctor(doctor);
        review.setHospital(hospital);
        review.setCreatedBy(reviewer);
        review.setRating(rating);
        review.setContent(content);

        return review;
    }

//    /**
//     * Generate a random review for a hospital
//     */
//    public static Review generateHospitalReview(Hospital hospital, MyUser reviewer) {
//        Review review = new Review();
//
//        double rating = generateWeightedRating();
//        String content = generateReviewContent(rating);
//
//        review.setHospital(hospital);
//        review.setCreatedBy(reviewer);
//        review.setRating(rating);
//        review.setContent(content);
//
//        return review;
//    }

    /**
     * Generate a weighted rating (more likely to be positive)
     * Distribution: 5 stars (40%), 4 stars (30%), 3 stars (15%), 2 stars (10%), 1 star (5%)
     */
   private static double generateWeightedRating() {
       int rand = random.nextInt(100);

       if (rand < 5) {
           return 0.0;
       } else if (rand < 10) {
           return 0.5;
       } else if (rand < 15) {
           return 1.0;
       } else if (rand < 20) {
           return 1.5;
       } else if (rand < 30) {
           return 2.0;
       } else if (rand < 40) {
           return 2.5;
       } else if (rand < 50) {
           return 3.0;
       } else if (rand < 65) {
           return 3.5;
       } else if (rand < 80) {
           return 4.0;
       } else if (rand < 90) {
           return 4.5;
       } else {
           return 5.0;
       }
   }

    /**
     * Generate review content based on rating
     */
    private static String generateReviewContent(double rating) {
        if (rating >= 4.5) {
            return getRandomFromList(EXCELLENT_REVIEWS);
        } else if (rating >= 3.5) {
            return getRandomFromList(GOOD_REVIEWS);
        } else if (rating >= 2.5) {
            return getRandomFromList(AVERAGE_REVIEWS);
        } else if (rating >= 1.5) {
            return getRandomFromList(POOR_REVIEWS);
        } else {
            return getRandomFromList(TERRIBLE_REVIEWS);
        }
    }

    /**
     * Get a random element from a list
     */
    private static String getRandomFromList(List<String> list) {
        return list.get(random.nextInt(list.size()));
    }

    /**
     * Generate multiple reviews for a hospital service
     */
    public static List<Review> generateMultipleHospitalServiceReviews(
            HosServ hosServ, List<MyUser> reviewers, Hospital hospital, int count) {

        return reviewers.stream()
                .limit(Math.min(count, reviewers.size()))
                .map(reviewer -> generateHospitalServiceReview(hosServ, reviewer, hospital))
                .toList();
    }

    /**
     * Generate multiple reviews for a doctor
     */
    public static List<Review> generateMultipleDoctorReviews(
            Doctor doctor, List<MyUser> reviewers, Hospital hospital, int count) {

        return reviewers.stream()
                .limit(Math.min(count, reviewers.size()))
                .map(reviewer -> generateDoctorReview(doctor, reviewer, hospital))
                .toList();
    }

//    /**
//     * Generate multiple reviews for a hospital
//     */
//    public static List<Review> generateMultipleHospitalReviews(
//            Hospital hospital, List<MyUser> reviewers, int count) {
//
//        return reviewers.stream()
//                .limit(Math.min(count, reviewers.size()))
//                .map(reviewer -> generateHospitalReview(hospital, reviewer))
//                .toList();
//    }
}