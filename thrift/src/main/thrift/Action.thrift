namespace java com

/**
 * @TypeDoc(
 *     description = "行为"
 * )
 */
enum Action {
        /**
         * @FieldDoc(
         *     description = "其他"
         * )
         */
        OTHER = 0,
        /**
         * @FieldDoc(
         *     description = "保护"
         * )
         */
        SHIELD = 1,
        /**
         * @FieldDoc(
         *     description = "置底"
         * )
         */
        SINK = 2,
        /**
         * @FieldDoc(
         *     description = "加降权,新的权重调整统一为加降权,1.0.7之前是提权"
         * )
         */
        UPGRADE = 3,
}